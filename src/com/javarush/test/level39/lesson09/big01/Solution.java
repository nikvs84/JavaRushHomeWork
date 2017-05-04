package com.javarush.test.level39.lesson09.big01;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("C:\\Users\\Admin\\Documents\\JavaRushHomeWork\\src\\com\\javarush\\test\\level39\\lesson09\\big01\\logs"));
//        System.out.println("getNumberOfUniqueIPs:\t" + logParser.getNumberOfUniqueIPs(null, new Date()));
//        System.out.println("getIPsForEvent:\t\t\t" + logParser.getIPsForEvent(Event.DOWNLOAD_PLUGIN, null, null));
//        System.out.println("getIPsForStatus:\t\t" + logParser.getIPsForStatus(Status.OK, null, null));
//        System.out.println("getIPsForUser:\t\t\t" + logParser.getIPsForUser("Eduard Petrovich Morozko", null, null));
//        System.out.println("getUniqueIPs:\t\t\t" + logParser.getUniqueIPs(logParser.parseDate("21.12.2012 0:0:0"), logParser.parseDate("14.10.2021 0:0:0")));
//        System.out.println(String.format("%s:\t\t\t%s", "getAllUsers", logParser.getAllUsers()));
//        System.out.println(String.format("%s:\t\t\t%s", "getDatesForUserAndEvent", logParser.getDatesForUserAndEvent("Eduard Petrovich Morozko", Event.WRITE_MESSAGE, null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDatesForUserAndEvent", logParser.getDatesForUserAndEvent("Vasya Pupkin", Event.DONE_TASK, null, null)));
//        System.out.println(String.format("%s:\t\t%s", "getDatesWhenSomethingFailed", logParser.getDatesWhenSomethingFailed(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDatesWhenErrorHappened", logParser.getDatesWhenErrorHappened(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDateWhenUserLoggedFirstTime", logParser.getDateWhenUserLoggedFirstTime("Eduard Petrovich Morozko", null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDateWhenUserSolvedTask", logParser.getDateWhenUserSolvedTask("Eduard Petrovich Morozko", 48, null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDateWhenUserDoneTask", logParser.getDateWhenUserDoneTask("Vasya Pupkin", 15, null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDatesWhenUserWroteMessage", logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko", null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getDatesWhenUserDownloadedPlugin", logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko", null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getNumberOfAllEvents", logParser.getNumberOfAllEvents(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getAllEvents", logParser.getAllEvents(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getEventsForIP", logParser.getEventsForIP("146.34.15.5", null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getEventsForUser", logParser.getEventsForUser("Eduard Petrovich Morozko", null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getFailedEvents", logParser.getFailedEvents(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getErrorEvents", logParser.getErrorEvents(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getNumberOfAttemptToSolveTask", logParser.getNumberOfAttemptToSolveTask(18, null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getNumberOfSuccessfulAttemptToSolveTask", logParser.getNumberOfSuccessfulAttemptToSolveTask(18, null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getAllSolvedTasksAndTheirNumber", logParser.getAllSolvedTasksAndTheirNumber(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "getAllDoneTasksAndTheirNumber", logParser.getAllDoneTasksAndTheirNumber(null, null)));
//        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get ip")));
//        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get user")));
//        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get date")));
//        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get event")));
//        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get status")));
//        System.out.println(logParser.getLogQuery("get ip for user = \"Amigo\""));
//        System.out.println(logParser.getLogQuery("get event for date = \"30.01.2014 12:56:22\""));
        String query = "get status for user = \"Amigo\"\"\"";
        System.out.println(query);
        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute(query)));
        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get ip for user = \"Amigo\"")));
        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get event for date = \"30.01.2014 12:56:22\"")));
        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute("get user for event = \"WRITE_MESSAGE\"")));
        query = "get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\"";
        System.out.println(logParser.getLogQuery(query));
        System.out.println(String.format("%s:\t\t\t%s", "execute", logParser.execute(query)));

    }
}
