package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 25.10.2016.
 */
public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    private static final String URL_FORMAT_TEST = "http://javarush.ru/testdata/big28data2.html";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document document;
        Elements vacancyElements = null;
        int i = 1;
        try {
            while (true) {
                document = getDocument(searchString, i);
                vacancyElements = document.select("div[id^=\"job_\"]");
                if (vacancyElements.isEmpty()) {
                    break;
                }
                for (Element element : vacancyElements) {
                    vacancyList.add(parseElementToVacancy(element));
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document;
        document = Jsoup.connect(String.format(URL_FORMAT, page, searchString)).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36").referrer("https://moikrug.ru").get();
        return document;
    }

    private Vacancy parseElementToVacancy(Element element) {
        Vacancy result = new Vacancy();
        String title = element.select("[class=\"title\"]").attr("title");
        result.setTitle(title);

        String salary = element.select("[class=\"salary\"]").text();
        if (salary != null) {
            result.setSalary(salary);
        } else result.setSalary("");

        String city = element.select("[class=\"location\"]").text();
        if (city != null) {
            result.setCity(city);
        }

        String companyName = element.select("[class=\"company_name\"]").select("a").text();
        if (companyName != null) {
            result.setCompanyName(companyName);
        }

        String siteName = "https://moikrug.ru";
        result.setSiteName(siteName);

        String url =siteName + element.select("[class=\"title\"]").select("a").attr("href");
        result.setUrl(url);

        return result;
    }

}
