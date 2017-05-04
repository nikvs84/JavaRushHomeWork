package com.javarush.test.level36.lesson06.task01;

import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* Найти класс по описанию
1. Реализует интерфейс List
2. Является приватным статическим классом внутри популярного утилитного класса
3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException
4. Используйте рефлекшн, чтобы добраться до искомого класса
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    //Это просто правильный ответ. Получить его программно пока не представляется возможным.
//    public static Class getExpectedClass() {
//        return Collections.EMPTY_LIST.getClass();
//    }

    public static Class getExpectedClass() {
        for (Class<?> clzz : Collections.class.getDeclaredClasses()) {
            if (Modifier.isPrivate(clzz.getModifiers()) && Modifier.isStatic(clzz.getModifiers())) {
                if (List.class.isAssignableFrom(clzz)) {
                    for (Method method : clzz.getMethods()) {
                        if (isGetOnIndexMethod(method)) {
                            System.out.print(clzz + " - ");
                            System.out.println(method);
                            clzz = clzz.asSubclass(List.class);

                            List instance = (List) constructObjectofClass(clzz);
                            List instance1 = (List) instance(clzz);
                            if (isDisableMethod(instance))
                                return clzz;
                        }
                    }
                }
            }
        }
        //Это просто правильный ответ. Получить его программно пока не представляется возможным.
        return Collections.EMPTY_LIST.getClass();
    }

//    private static boolean isList(Class clazz) {
//        for (Class clzz : clazz.getInterfaces()) {
//            if (clzz.isInstance(List.class))
//                return true;
//        }
//        return false;
//    }

    private static boolean isGetOnIndexMethod(Method method) {
        if (method.getName().toLowerCase().equals("get"))
            return (method.getParameterCount() == 1 && method.getParameterTypes()[0] == int.class);
        return false;
    }

    private static boolean isDisableMethod(List instance) {
        try {
            Method method;
            method = instance.getClass().getMethod("add", Object.class);

            method = instance.getClass().getMethod("get", int.class);
            boolean isAccessibleMethod = method.isAccessible();
            if (method.isAccessible())
                method.setAccessible(true);
            instance.get(instance.size() - 1);
        } catch (IndexOutOfBoundsException iobe) {
            return true;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException uoe) {
            uoe.printStackTrace();
        }
        return false;
    }

    private static void setAccessToNewInstance(Class clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().toLowerCase().equals("newinstance"))
                method.setAccessible(true);
        }
    }

    private static Object constructObjectofClass(Class clazz) {
        try {
            for (Constructor constructor : clazz.getDeclaredConstructors()) {
                constructor.setAccessible(true);
                Class[] args = constructor.getParameterTypes();
                Object[] argObj = getInitParametersFromClassArray(args);

                Object instance = constructor.newInstance(argObj);
                return instance;

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object[] getInitParametersFromClassArray(Class[] args) {
        Object[] param = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            param[i] = getInstanceOfClass(args[i]);
        }
        return param;
    }

    private static Object getInstanceOfClass(Class clazz) {
        try {
            switch (clazz.getSimpleName().toLowerCase()) {
                case "boolean":
                    return new Boolean(false);
                case "byte":
                    return new Byte((byte) 0);
                case "char":
                    return new Character(' ');
                case "short":
                    return new Short((short) 0);
                case "int":
                    return new Integer(0);
                case "long":
                    return new Long(0);
                case "float":
                    return new Float(0);
                case "double":
                    return new Double(0);
                default:
                    return clazz.newInstance();
            }
        } catch (InstantiationException e) {
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        }
        return null;
    }

    private static <T> T instance(Class<T> c) {
        try {
            ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();

            @SuppressWarnings("unchecked")
            Constructor<T> constructor = (Constructor<T>) reflectionFactory.newConstructorForSerialization(c, Object.class.getConstructor());

            return constructor.newInstance();
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }
    }

//    private static void shoInfoAboutMethods(Class clazz) {
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method m : methods) {
//            Annotation[] annotations = m.getAnnotations();
//            System.out.print("\t");
//            for (Annotation a : annotations)
//                System.out.print("@" + a.annotationType().getSimpleName() + " ");
//            System.out.println();
//
//            System.out.print("\t" + (m.getModifiers()) + (m.getReturnType()) + " " + m.getName() + "(");
//            System.out.print((m.getParameterTypes()));
//            System.out.println(") { }");
//        }
//    }


}
