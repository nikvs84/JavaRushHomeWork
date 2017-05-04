package com.javarush.test.level38.lesson06.home01;

/* Фабрика исключений
Создайте класс - фабрику исключений, который содержит один статический метод, возвращающий нужное исключение.
Этот метод должен принимать один параметр - энум.
Если передан энум:
* ExceptionApplicationMessage, верните исключение Exception
* ExceptionDBMessage, верните исключение RuntimeException
* ExceptionUserMessage, верните Error
иначе верните IllegalArgumentException без сообщения.

В качестве сообщения (message) для каждого возвращаемого объекта используйте элементы энума, в которых все знаки
подчеркивания замените на пробелы. Сообщение должно быть в нижнем регистре за исключением первого символа.
Например, сообщение для ExceptionApplicationMessage.SOCKET_IS_CLOSED - "Socket is closed".

Верните класс созданной фабрики в методе Solution.getFactoryClass.

Энумы не меняйте.
*/

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    private static class ExceptionFactory {
        public static Throwable getInstance(Enum e) {
            if (e != null) {
                String message = capitalizeString(e.name());
                if (e instanceof ExceptionApplicationMessage)
                    return new Exception(message);
                if (e instanceof ExceptionDBMessage)
                    return new RuntimeException(message);
                if (e instanceof ExceptionUserMessage)
                    return new Error(message);
            }
            return new IllegalArgumentException();
        }
    }

    private static String capitalizeString(String string) {
        if (string.length() == 1)
            return string.toUpperCase();
        String result = string.toLowerCase().replaceAll("_", " ");
        result = result.substring(0, 1).toUpperCase() + result.substring(1);
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = getFactoryClass();
        Method method = clazz.getMethod("getInstance", Enum.class);
        Throwable throwable = (Throwable) method.invoke(clazz, ExceptionApplicationMessage.SOCKET_IS_CLOSED);
        throwable.printStackTrace();
    }
}
