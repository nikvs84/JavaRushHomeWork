package com.javarush.test.level35.lesson10.bonus01;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.*;

/* ClassLoader - что это такое?
Реализуйте логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/
public class Solution {
    public static void main(String[] args) {
//        Set<? extends Animal> allAnimals = getAllAnimals("C://pathToClasses/");
        Set<? extends Animal> allAnimals =
                getAllAnimals("C:\\Users\\Admin\\Documents\\JavaRushHomeWork\\out\\production\\JavaRushHomeWork\\com\\javarush\\test\\level35\\lesson10\\bonus01\\data");
        System.out.println(allAnimals);
    }


    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {

        Set<Animal> result = new HashSet<>();
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";
        File dir = new File(pathToAnimals);
        String[] modules = dir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".class");
            }
        });

        for (String m : modules) {
            try {
                final String finalPathToAnimals = pathToAnimals;
                ClassLoader loader = new ClassLoader() {
                    @Override
                    public Class<?> findClass(String className) throws ClassNotFoundException {
                        try {
                            byte b[] = fetchClassFromFS(finalPathToAnimals + className + ".class");
                            return defineClass(null, b, 0, b.length);
                        }
                        catch (FileNotFoundException ex) {
                            return super.findClass(className);
                        }
                        catch (IOException ex) {
                            return super.findClass(className);
                        }
                    }
                };

                String mName = m.substring(0, m.length()-6);
                Class clazz = loader.loadClass(mName);
                boolean hasInterface = false;
                Class[] interfaces = clazz.getInterfaces();
                for(Class i : interfaces) {
                    if(Animal.class.equals(i)) {
                        hasInterface = true;
                        break;
                    }
                }

                if(! hasInterface ) continue;
                boolean hasConstructor = false;
                Constructor[] constructors = clazz.getConstructors();
                for (Constructor c : constructors) {
                    if( Modifier.isPublic(c.getModifiers()) && c.getParameterTypes().length==0 ) {
                        hasConstructor = true;
                        break;
                    }
                }

                if(!hasConstructor) continue;
                result.add((Animal) clazz.newInstance());
            }
            catch (Exception e) { }
        }
        return result;
    }

    private static byte[] fetchClassFromFS(String path) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream(new File(path));
        // Get the size of the file
        long length = new File(path).length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + path);
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }


    /*
    * Не проходит проверку*/
/*
    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> result = new HashSet<>();
        if (!pathToAnimals.endsWith("/") && !pathToAnimals.endsWith("\\"))
            pathToAnimals += "/";
        try {
            File classDir = new File(pathToAnimals);
//            ClassLoader classLoader = new CurrentClassLoader(getMapping(classDir));
            ClassLoader classLoader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    byte[] bytes = getByteArrayFromFile(getMapping(classDir).get(name));
                    Class result = defineClass(null, bytes, 0, bytes.length);
                    if (result != null)
                        return result;
                    else
                        return super.findClass(name);
                }
            };
            List<Class<?>> classList = new ArrayList<>();
            for (File f : classDir.listFiles()) {
                if (f.getName().toLowerCase().endsWith(".class")) {
                    String name = f.getName().substring(0, f.getName().length() - 6);
                    classList.add(classLoader.loadClass(name));
                }
            }
            for (Class clazzz : classList) {
                if (isAnimal(clazzz))
                    result.add((Animal) clazzz.newInstance());
            }
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isAnimal(Class<?> clazz) {
        boolean result = false;
        if (isLoadableClass(clazz)) {
            Class<?>[] interfaces = clazz.getInterfaces();
            for (Class<?> interfaze : interfaces) {
                if (interfaze.equals(Animal.class)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static Map<String, String> getMapping(File classDir) {
        Map<String, String> result = new HashMap<>();
        for (File file : classDir.listFiles()) {
            String fileName = file.getName().substring(0, file.getName().length() - 6);
            result.put(fileName, file.getAbsolutePath());
        }
        return result;
    }

    public static boolean isLoadableClass(Class clazz) {
        if (
                Modifier.isAbstract(clazz.getModifiers())
                        || Modifier.isProtected(clazz.getModifiers())
                        || Modifier.isPrivate(clazz.getModifiers())
                )
            return false;
        Constructor constructor;
        for (Constructor constr : clazz.getConstructors()) {
            if (constr == null)
                continue;
            if (constr.getParameterCount() == 0 &&
                    !Modifier.isPrivate(constr.getModifiers()) &&
                    !Modifier.isProtected(constr.getModifiers())) {
                return true;
            }
        }

        return false;
    }

    public static byte[] getByteArrayFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() > Integer.MAX_VALUE)
            return null;
        byte[] bytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static class CurrentClassLoader extends ClassLoader {
//        private Map<String, String> mappings;
//
//        public CurrentClassLoader(Map<String, String> mappings) {
//            this.mappings = mappings;
//        }
//
////        @Override
////        public Class<?> loadClass(String name) throws ClassNotFoundException {
////            if (!mappings.containsKey(name))
////                return super.findSystemClass(name);
////
////            byte[] bytes = getByteArrayFromFile(mappings.get(name));
////            if (bytes != null) {
////
//////                return defineClass(null, bytes, 0, bytes.length);
////                return defineClass(bytes, 0, bytes.length);
////            }
////
////            return null;
////        }
//
//        @Override
//        protected Class<?> findClass(String name) throws ClassNotFoundException {
//            if (!mappings.containsKey(name))
//                return super.findSystemClass(name);
//
//            byte[] bytes = getByteArrayFromFile(mappings.get(name));
//            Class result = null;
//            if (bytes != null) {
//                result = defineClass(null, bytes, 0, bytes.length);
//                if (result == null)
//                    result = super.findClass(name);
//            }
//            return result;
//        }
//
//        public byte[] getByteArrayFromFile(String fileName) {
//            File file = new File(fileName);
//            if (!file.exists() || file.length() > Integer.MAX_VALUE)
//                return null;
//            byte[] bytes = new byte[(int) file.length()];
//            try (FileInputStream fis = new FileInputStream(file)) {
//                fis.read(bytes);
//                return bytes;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
*/
}
