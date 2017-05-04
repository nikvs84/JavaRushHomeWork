package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public static final class Constants {
        public static final String string1 = "Server is not accessible for now.";
        public static final String string2 = "User is not authorized.";
        public static final String string3 = "User is banned.";
        public static final String string4 = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.string1);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.string1, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.string2);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.string2, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.string3);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.string3, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.string4);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.string4, cause);
        }
    }
}
