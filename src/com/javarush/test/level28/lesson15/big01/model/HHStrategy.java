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
 * Created by Admin on 24.10.2016.
 */
public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String URL_FORMAT_TEST = "http://javarush.ru/testdata/big28data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancyList = new ArrayList<>();
        Document document;
        Elements vacancyElements = null;
        int i = 0;
        while (true) {

            try {
                document = getDocument(searchString, i);
                vacancyElements = document.select("[data-qa=\"vacancy-serp__vacancy\"]");
            } catch (IOException ignore) {

            }
            if (vacancyElements.isEmpty()) {
                break;
            }
            for (Element vacancy : vacancyElements) {
                vacancyList.add(parseElementToVacancy(vacancy));
            }
            i++;
        }

        return vacancyList;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = Jsoup.connect(String.format(URL_FORMAT, searchString, page)).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36").referrer("https://hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2").get();
        return document;
    }

    private Vacancy parseElementToVacancy(Element element) {
        Vacancy result = new Vacancy();
        String title = element.select("[data-qa=\"vacancy-serp__vacancy-title\"]").text();
            result.setTitle(title);

        String salary = element.select("[data-qa=\"vacancy-serp__vacancy-compensation\"]").text();
        if (salary != null) {
            result.setSalary(salary);
        } else result.setSalary("");

        String city = element.select("[data-qa=\"vacancy-serp__vacancy-address\"]").text();
        if (city != null) {
            result.setCity(city);
        }

        String companyName = element.select("[data-qa=\"vacancy-serp__vacancy-employer\"]").text();
        if (companyName != null) {
            result.setCompanyName(companyName);
        }

        String siteName = "https://hh.ua";
            result.setSiteName(siteName);

        String url = element.select("a[data-qa=\"vacancy-serp__vacancy-title\"]").first().attr("href");
        result.setUrl(url);

        return result;
    }

}
