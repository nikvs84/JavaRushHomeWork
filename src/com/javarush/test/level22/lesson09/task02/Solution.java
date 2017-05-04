package com.javarush.test.level22.lesson09.task02;

import java.util.HashMap;
import java.util.Map;

/* Формируем Where
Сформируйте часть запроса WHERE используя StringBuilder.
Если значение null, то параметр не должен попадать в запрос.
Пример:
{"name", "Ivanov", "country", "Ukraine", "city", "Kiev", "age", null}
Результат:
"name = 'Ivanov' and country = 'Ukraine' and city = 'Kiev'"
*/
public class Solution {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", "21");

        System.out.println(getCondition(map));
    }

    public static StringBuilder getCondition(Map<String, String> params) {
        StringBuilder query = new StringBuilder();

        for (Map.Entry<String, String> item : params.entrySet()) {
            String key = item.getKey();
            String val = item.getValue();
            if (val == null)
                continue;
            String split = " and ";
            if (query.length() == 0)
                split = "";
            String app = split +  key + " = " + "'" + val + "'";
            query.append(app);
        }
        return query;
    }
}
