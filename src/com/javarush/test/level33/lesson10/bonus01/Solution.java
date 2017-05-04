package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.regex.Pattern;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, document);
            StringWriter stringWriter = new StringWriter();

            NodeList nodeList = document.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                replaceCDATAText(node, document);
                if (node.getNodeName().equals(tagName)) {
                    Comment comm = document.createComment(comment);
                    node.getParentNode().insertBefore(comm, node);
                }

            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (ParserConfigurationException |JAXBException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void replaceCDATAText(Node node, Document document) {
        if (node.getNodeType() == 3 && Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find()) {
            Node cdataNode = document.createCDATASection(node.getNodeValue());
            node.getParentNode().replaceChild(cdataNode, node);
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            replaceCDATAText(nodeList.item(i), document);
        }
    }

    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        public String[] needCDATA = new String[]{"need CDATA because of < and >", "1", "2"};
    }

    public static void main(String[] args) {
        String result = Solution.toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }
}
/*
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        String commentTag = "<!--" + comment + "-->";
        List<String> list = new ArrayList<>();
        StringWriter writer = new StringWriter();
        String tag = "<" + tagName;
        String result = "";
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);
            StringReader stringReader = new StringReader(writer.toString());
            try (BufferedReader reader = new BufferedReader(stringReader)) {
                while (true) {
                    String tagString = reader.readLine();
                    if (tagString == null)
                        break;
                    if (tagString.contains(tag))
                        if (!tagString.startsWith("<![CDATA")) {
                            String spaces = tagString.substring(0, tagString.indexOf('<'));
                            list.add(spaces + commentTag);
                        }
                    list.add(tagString);
                }
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

        StringBuilder xmlString = new StringBuilder();

        for (int i = 0; i < list.size() - 1; i++) {
            xmlString.append(list.get(i) + "\n");
        }
        xmlString.append(list.get(list.size() - 1));
        return xmlString.toString();
    }
*/
