package com.javarush.test.level36.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
        fillClassList(packageName);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("C:\\Users\\Admin\\Documents\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level36\\lesson10\\bonus01\\data\\second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz: hiddenClasses) {
            if (HiddenClass.class.isAssignableFrom(clazz)) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase()))
                    try {
                        Constructor constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);
                        return (HiddenClass) constructor.newInstance();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
            }
        }
        return null;
    }

    private List<Class> fillClassList(String packageName) {
        ClassLoader ourClassLoader = new ClassLoader() {
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                try (FileInputStream fis = new FileInputStream(name)) {
                    String className = name.substring(0, name.length() - 6);
                    byte[] bytes = new byte[fis.available()];
                    fis.read(bytes);
                    return defineClass(null, bytes, 0, bytes.length);
                } catch (FileNotFoundException e) {
                    return super.findClass(name);
                } catch (IOException e) {
                    return super.findClass(name);
                }
            }
        };
        File clasDir = new File(packageName);

//        List<Class> result = new ArrayList<>();
        String[] fileNames = clasDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        });
        for (String fileName : fileNames) {
            try {
                hiddenClasses.add(ourClassLoader.loadClass(packageName + clasDir.separator + fileName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return hiddenClasses;
    }

}
