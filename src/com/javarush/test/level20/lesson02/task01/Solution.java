package com.javarush.test.level20.lesson02.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* Читаем и пишем в файл: Human
Реализуйте логику записи в файл и чтения из файла для класса Human
Поле name в классе Human не может быть пустым
В файле your_file_name.tmp может быть несколько объектов Human
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

//            File your_file_name = File.createTempFile("your_file_name", null);
            String your_file_name = "d:\\a00.txt";
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home"), new Asset("car"));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны
            boolean b = true;
            if (ivanov.name.equals(somePerson.name)) {
                System.out.println(ivanov.name);
            } else {
                b = false;
            }
            if (ivanov.assets.size() == somePerson.assets.size()) {
                int size = ivanov.assets.size();
                for (int i = 0; i < size; i++) {
                    Asset assetI = ivanov.assets.get(i);
                    Asset assetS = somePerson.assets.get(i);
                    if (assetI.getName().equals(assetS.getName())) {
                        System.out.println(assetI.getName());
                    } else {
                        b = false;
                    }

                    if (assetI.getPrice() == assetS.getPrice()) {
                        System.out.println(assetI.getPrice());
                    } else {
                        b = false;
                    }
                }
            } else {
                b = false;
            }
            System.out.println(b);

            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }


    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(this.name);
            if (this.assets == null)
                writer.println("no");
            else {
                writer.println(assets.size());
                for (Asset asset : this.assets) {
                    writer.println(asset.getName());
                    writer.println("" + asset.getPrice());
                }
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.name = reader.readLine();
            String assetSize = reader.readLine();
            if ("no".equals(assetSize))
                return;
            else {
                int size = Integer.parseInt(assetSize);
                for (int i = 0; i < size; i++) {
                    String assetName = reader.readLine();
                    String assePprice = reader.readLine();
                    double price = Double.parseDouble(assePprice);
                    Asset asset = new Asset(assetName);
                    asset.setPrice(price);
                    this.assets.add(asset);
                }
            }
            reader.close();
        }
    }
}
