package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Admin on 02.11.2016.
 */
public class BotClient extends Client {

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%02d", ThreadLocalRandom.current().nextInt(0, 100));
    }

    public class BotSocketThread extends Client.SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (!message.contains(": "))
                return;
            String[] splitMessage = message.split(": ", 2);
            String userName = splitMessage[0];
            String messageText = splitMessage[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            Calendar calendar;
            boolean isQuestion = true;
            switch (messageText.toLowerCase()) {
                case ("дата"):
                    dateFormat.applyPattern("d.MM.YYYY");
                    break;
                case ("день"):
                    dateFormat.applyPattern("d");
                    break;
                case ("месяц"):
                    dateFormat.applyPattern("MMMM");
                    break;
                case ("год"):
                    dateFormat.applyPattern("YYYY");
                    break;
                case ("время"):
                    dateFormat.applyPattern("H:mm:ss");
                    break;
                case ("час"):
                    dateFormat.applyPattern("h");
                    break;
                case ("минуты"):
                    dateFormat.applyPattern("m");
                    break;
                case ("секунды"):
                    dateFormat.applyPattern("s");
                    break;
                default:
                    isQuestion = false;
            }

            if (isQuestion) {
                calendar = new GregorianCalendar();
                String answerMessage = String.format("Информация для %s: %s", userName, dateFormat.format(calendar.getTime()));
                sendTextMessage(answerMessage);
            }
        }
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
