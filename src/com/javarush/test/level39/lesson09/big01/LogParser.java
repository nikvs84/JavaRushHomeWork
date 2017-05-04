package com.javarush.test.level39.lesson09.big01;

import com.javarush.test.level39.lesson09.big01.query.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class  LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
//    private List<LogRecord> recordList;

    public LogParser(Path logDir) {
        this.logDir = logDir;
        linesList = getLinesList();
    }

    /*
    *Implement IPQuery
    * */
    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getIpSet(null, after, before);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getIpSet(user, after, before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getIpSet(event, after, before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getIpSet(status, after, before);
    }

    /*
    *Implement UserQuery
    * */
    @Override
    public Set<String> getAllUsers() {
        return getUserSet(null, null, null);
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getUserSet(null, after, before).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getUserSet(ip, after, before);
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getUserSet(Event.LOGIN, after, before);
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getUserSet(Event.DOWNLOAD_PLUGIN, after, before);
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getUserSet(Event.WRITE_MESSAGE, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getUserSet(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> userSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getTaskNumber() == task && record.getEvent().equals(Event.SOLVE_TASK))
                userSet.add(record.getUser());
        }
        return getUserSet(new Integer(task), after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getUserSet(Event.DONE_TASK, after, before);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> userSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getTaskNumber() == task && record.getEvent().equals(Event.DONE_TASK))
                userSet.add(record.getUser());
        }
        return userSet;
    }

    /*
    *Implement DateQuery
    * */
    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getUser().equals(user) && record.getEvent().equals(event))
                dateSet.add(record.getDate());
        }
        return dateSet;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getStatus().equals(Status.FAILED) && isDateInside(after, before, record.getDate()))
                dateSet.add(record.getDate());
        }
        return dateSet;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getStatus().equals(Status.ERROR) && isDateInside(after, before, record.getDate()))
                dateSet.add(record.getDate());
        }
        return dateSet;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date result = new Date(Long.MAX_VALUE);
        boolean hasDate = false;
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getEvent().equals(Event.LOGIN))
                if (record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                    if (record.getDate().getTime() < result.getTime()) {
                        hasDate = true;
                        result = record.getDate();
                    }
        }
        if (hasDate)
            return result;
        else
            return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date result = new Date(Long.MAX_VALUE);
        boolean hasDate = false;
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getUser().equals(user) && record.getEvent().equals(Event.SOLVE_TASK))
                if (record.getTaskNumber() == task && isDateInside(after, before, record.getDate()))
                    if (record.getDate().getTime() < result.getTime()) {
                        hasDate = true;
                        result = record.getDate();
                    }
        }
        if (hasDate)
            return result;
        else
            return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = null;
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getEvent().equals(Event.DONE_TASK))
                if (record.getTaskNumber() == task && record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                    date = record.getDate();
        }
        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getEvent().equals(Event.WRITE_MESSAGE))
                if (record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                    dateSet.add(record.getDate());
        }
        return dateSet;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                if (record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                    dateSet.add(record.getDate());
        }
        return dateSet;
    }

    /*
    * Implement EventQuery*/

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getIp().equals(ip) && isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getUser().equals(user) && isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getStatus().equals(Status.FAILED) && isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> eventSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getStatus().equals(Status.ERROR) && isDateInside(after, before, record.getDate()))
                eventSet.add(record.getEvent());
        }
        return eventSet;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int numberOfAttempts = 0;
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getTaskNumber() == task && record.getEvent().equals(Event.SOLVE_TASK))
                if (isDateInside(after, before, record.getDate()))
                    numberOfAttempts++;
        }
        return numberOfAttempts;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int numberOfSuccessAttempts = 0;
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (record.getTaskNumber() == task && record.getEvent().equals(Event.SOLVE_TASK))
                if (record.getStatus().equals(Status.OK) && isDateInside(after, before, record.getDate()))
                    numberOfSuccessAttempts++;
        }
        return numberOfSuccessAttempts;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        return getTaskMap(Event.SOLVE_TASK, after, before);
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        return getTaskMap(Event.DONE_TASK, after, before);
    }

    /*
    * Implement QLQuery*/

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        LogQuery logQuery = new LogQuery(query);
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (logQuery.getqAnd() != null
                    && !isDateInside(logQuery.getqDateAfter(), logQuery.getqDateBefore(), record.getDate()))
                continue;
            switch (logQuery.getqGet()) {
                case "ip":
                    if (isFieldMatchForQLQuery(logQuery.getqFor(), logQuery.qForValue, record))
                            result.add(record.getIp());
                    break;
                case "user":
                    if (isFieldMatchForQLQuery(logQuery.getqFor(), logQuery.qForValue, record))
                        result.add(record.getUser());
                    break;
                case "date":
                    if (isFieldMatchForQLQuery(logQuery.getqFor(), logQuery.qForValue, record))
                        result.add(record.getDate());
                    break;
                case "event":
                    if (isFieldMatchForQLQuery(logQuery.getqFor(), logQuery.qForValue, record))
                        result.add(record.getEvent());
                    break;
                case "status":
                    if (isFieldMatchForQLQuery(logQuery.getqFor(), logQuery.qForValue, record))
                        result.add(record.getStatus());
            }
        }

        return result;
    }

    /*
    * Утилитные методы и классы*/
    private Set<String> getIpSet(Object recordField, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()) && isFieldMatchForIPQuery(recordField, record)) {
                ipSet.add(record.getIp());
            }

        }
        return ipSet;
    }

    private Set<String> getUserSet(Object recordField, Date after, Date before) {
        Set<String> userSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()) && isFieldMatchForUserQuery(recordField, record)) {
                userSet.add(record.getUser());
            }
        }
        return userSet;
    }

    private Map<Integer, Integer> getTaskMap(Event event, Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        Integer taskAttemptCount = null;
        int taskNumber;
        for (LogRecord record : getParsedRecordList(logDir)) {
            taskNumber = record.getTaskNumber();
            if (record.getEvent().equals(event) && isDateInside(after, before, record.getDate())) {
                if ((taskAttemptCount = result.get(taskNumber)) != null)
                    result.put(taskNumber, (taskAttemptCount + 1));
                else
                    result.put(taskNumber, 1);
            }
        }
        return result;
    }

    private Set<Date> getAllDates(Date after, Date before) {
        Set<Date> dateSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()))
                dateSet.add(record.getDate());
        }
        return dateSet;
    }

    private Set<Status> getAllStatus(Date after, Date before) {
        Set<Status> statusSet = new HashSet<>();
        for (LogRecord record : getParsedRecordList(logDir)) {
            if (isDateInside(after, before, record.getDate()))
                statusSet.add(record.getStatus());
        }
        return statusSet;

    }

    private boolean isDateInside(Date after, Date before, Date currentDate) {
        if (after != null) {
            if (currentDate.getTime() < after.getTime())
                return false;
        }
        if (before != null) {
            if (currentDate.getTime() > before.getTime())
                return false;
        }
        return true;
    }

    private boolean isFieldMatchForIPQuery(Object recordField, LogRecord record) {
        boolean criteria = false;
        if (recordField == null)
            return true;
        if (recordField instanceof String)
            criteria = record.getUser().equals(recordField);
        else if (recordField instanceof Event)
            criteria = record.getEvent().equals(recordField);
        else if (recordField instanceof Status)
            criteria = record.getStatus().equals(recordField);
        return criteria;
    }

    private boolean isFieldMatchForUserQuery(Object recordField, LogRecord record) {
        boolean criteria = false;
        if (recordField == null)
            return true;
        if (recordField instanceof String)
            criteria = record.getIp().equals(recordField);
        else if (recordField instanceof Event)
            criteria = record.getEvent().equals(recordField);
        else if (recordField instanceof Status)
            criteria = record.getStatus().equals(recordField);
        else if (recordField instanceof Integer)
            criteria = Integer.valueOf(record.getTaskNumber()).equals(recordField);
        return criteria;
    }

    private boolean isFieldMatchForQLQuery(String qFor, Object qForValue, LogRecord record) {
        boolean criteria = false;
        switch (qFor) {
            case "ip":
                criteria = record.getIp().equals(qForValue);
                break;
            case "user":
                criteria = record.getUser().equals(qForValue);
                break;
            case "date":
                criteria = record.getDate().equals(qForValue);
                break;
            case "event":
                criteria = record.getEvent().equals(qForValue);
                break;
            case "status":
                criteria = record.getStatus().equals(qForValue);
                break;
        }
        return criteria;
    }

    private List<LogRecord> getParsedRecordList(Path logDir) {
        List<LogRecord> recordList = new ArrayList<>();
        File[] files = logDir.toFile().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });
        try {
            for (File file : files) {
                for (String record : Files.readAllLines(file.toPath(), Charset.defaultCharset())) {
                    recordList.add(new LogRecord(record));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }

    public Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LogQuery getLogQuery(String query) {
        return new LogQuery(query);
    }

    /*
    * Класс записи лога*/
    private class LogRecord {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int taskNumber;
        private Status status;

        public LogRecord(String ip, String user, Date date, Event event, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.status = status;
        }

        public LogRecord(String record) {
            String[] strings = record.split("\\t");
            this.ip = strings[0].trim();
            this.user = strings[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
            try {
                date = dateFormat.parse(strings[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String eventAndParameter[] = strings[3].split(" ");
            event = Event.valueOf(eventAndParameter[0]);
            if (eventAndParameter.length > 1) {
                taskNumber = Integer.parseInt(eventAndParameter[1]);
            } else taskNumber = 0;
            status = Status.valueOf(strings[4]);
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }
    }

    /*
    * Класс запроса*/
    private class LogQuery {
        private String qGet;
        private String qFor;
        private Object qForValue;
        private String qAnd;
        private Date qDateAfter;
        private Date qDateBefore;


        public LogQuery(String query) {
            String[] strings = query.split("=");
            String[] leftSide = strings[0].split(" ");
            qGet = leftSide[1].trim().toLowerCase();
            qFor = leftSide[3].trim().toLowerCase();
            String[] rightSide = strings[1].split("and");
            String forValue = rightSide[0].trim().replaceAll("\"", "");
            forValue.replaceAll("\"*", "");
            switch (qFor) {
                case "ip":
                    qForValue = forValue;
                    break;
                case "user":
                    qForValue = forValue;
                    break;
                case "date":
                    qForValue = parseDate(forValue);
                    break;
                case "event":
                    qForValue = Event.valueOf(forValue.toUpperCase());
                    break;
                case "status":
                    qForValue = Status.valueOf(forValue.toUpperCase());
                    break;
            }

            if (rightSide.length < 2)
                return;
            String[] afterqAnd = rightSide[1].trim().split(" ");
            qAnd = afterqAnd[0].trim();
            String dateAfter = rightSide[1].replaceFirst("date between", "").replaceAll("\"*", "").trim();
            qDateAfter = parseDate(dateAfter);
            String dateBefore = rightSide[2].replaceAll("\"*", "");
            qDateBefore = parseDate(dateBefore);
        }

        public String getqGet() {
            return qGet;
        }

        public String getqFor() {
            return qFor;
        }

        public Object getqForValue() {
            return qForValue;
        }

        public String getqAnd() {
            return qAnd;
        }

        public Date getqDateAfter() {
            return qDateAfter;
        }

        public Date getqDateBefore() {
            return qDateBefore;
        }

        @Override
        public String toString() {
            return "LogQuery{" +
                    "qGet='" + qGet + '\'' +
                    ", qFor='" + qFor + '\'' +
                    ", qForValue=" + qForValue +
                    ", qAnd='" + qAnd + '\'' +
                    ", qDateAfter=" + qDateAfter +
                    ", qDateBefore=" + qDateBefore +
                    '}';
        }
    }

    /*
    * Вспомогательные поля и методы для проверки*/
    private void addDateEntity(Date after, Date before, Set<Date> enteties, String[] parts) {
        Date lineDate = getDate(parts[2]);
        long lineDateTime = getDate(parts[2]).getTime();

        if (isCompatibleDate(lineDateTime, after, before)) {
            enteties.add(lineDate);
        }
    }

    private Date getDate(String part) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(part);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime >= after.getTime() && lineDateTime <= before.getTime()) {
                return true;
            }
        }
        return false;
    }

    private List<String> getLinesList() {
        String[] files = logDir.toFile().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files) {
            try {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private List<String> linesList;

}
