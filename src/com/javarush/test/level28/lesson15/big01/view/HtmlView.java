package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 25.10.2016.
 */
public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().toString().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        String html = getUpdatedFileContent(vacancies);
        updateFile(html);
    }

    @Override
    public void setController(Controller controller) {

        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        String result = "";
        try {
            Document document = getDocument();
            Element templateElement = document.getElementsByClass("template").first();
            Element insertElement = templateElement.clone();
            insertElement.removeClass("template");
            insertElement.removeAttr("style");
            removeRecords(document, "tr[class~=^vacancy$]");

            for (Vacancy vacancy : vacancyList) {
                Element newElement = insertValuesToRecord(insertElement, vacancy);
                String insertHtml = newElement.outerHtml();
                templateElement.before(insertHtml);
            }

            result = document.html();
        } catch (IOException e) {
            e.printStackTrace();
            result = "Some exception occurred";
        }
        return result;
    }

    private void updateFile(String html) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(html);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        Document document = Jsoup.parse(new File(filePath), "UTF-8");
        return document;
    }

    private void removeRecords(Document document, String param) {
        Elements deleteElements = document.select(param);
        for (Element element : deleteElements) {
            element.remove();
        }
    }

    private Element insertValuesToRecord(Element element, Vacancy vacancy) {
        Element clone = element.clone();
        Element city = clone.select("td[class=\"city\"]").first();
        city.html(vacancy.getCity());
        Element companyName = clone.select("td[class=\"companyName\"]").first();
        companyName.html(vacancy.getCompanyName());
        Element salary = clone.select("td[class=\"salary\"]").first();
        salary.html(vacancy.getSalary());
        Element title = clone.select("a[href=\"url\"]").first();
        title.html(vacancy.getTitle());
        title.attr("href", vacancy.getUrl());
        return clone;
    }

}
