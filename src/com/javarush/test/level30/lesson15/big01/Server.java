package com.javarush.test.level30.lesson15.big01;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Admin on 31.10.2016.
 */
public class Server {

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> pair: connectionMap.entrySet()) {
                pair.getValue().send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Не удалось отправить сообщение.");
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            String userName;
            Message answerMessage;
            do {
                connection.send(new Message(MessageType.NAME_REQUEST));
                answerMessage = connection.receive();
                userName = answerMessage.getData();
                if (userName != null && !userName.isEmpty() && !connectionMap.containsKey(userName)) {
                    connectionMap.put(userName, connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return userName;
                }
            } while (true);
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName))
                connection.send(new Message(MessageType.USER_ADDED, name));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            String messageData;
            do {
                message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    messageData = String.format("%s: %s", userName, message.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, messageData));
                } else {
                    ConsoleHelper.writeMessage("Сообщение не является текстом.");
                }
            } while (true);
        }

        public void run() {
            ConsoleHelper.writeMessage("Установленно соединение с адресом " + socket.getRemoteSocketAddress());
            String clientName = null;
            //Создаем Connection
            try (Connection connection = new Connection(socket)) {
                //Выводить сообщение, что установлено новое соединение с удаленным адресом
                ConsoleHelper.writeMessage("Подключение к порту: " + connection.getRemoteSocketAddress());
                //Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента
                clientName = serverHandshake(connection);
                //Рассылать всем участникам чата информацию об имени присоединившегося участника (сообщение с типом USER_ADDED)
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, clientName));
                //Сообщать новому участнику о существующих участниках
                sendListOfUsers(connection, clientName);
                //Запускать главный цикл обработки сообщений сервером
                serverMainLoop(connection, clientName);


            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с удаленным адресом");
            }

            //После того как все исключения обработаны, удаляем запись из connectionMap
            connectionMap.remove(clientName);
            //и отправлялем сообщение остальным пользователям
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, clientName));

            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто");
        }
    }

    public static void main(String[] args) {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            ConsoleHelper.writeMessage("Сервер запущен.");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (Exception e) {
            try {
                serverSocket.close();
            } catch (IOException e1) {
            }
        }
    }
}
