package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.util.Date;

public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean isMale;
    private Country country;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public static enum Country {
        UKRAINE("Ukraine"),
        RUSSIA("Russia"),
        OTHER("Other");

        private String name;

        private Country(String name) {
            this.name = name;
        }

        public String getDisplayedName() {
            return this.name;
        }
    }

    public void save(PrintWriter writer) {
//        PrintWriter writer = new PrintWriter(outputStream, true);

        String firstNameExist = firstName == null ? "no" : "yes";
        writer.println(firstNameExist);
        if (firstName != null)
           writer.println(firstName);

        String lastNameExist = lastName == null ? "no" : "yes";
        writer.println(lastNameExist);
        if (lastName != null)
            writer.println(lastName);

        String birthDateExist = birthDate == null ? "no" : "yes";
        writer.println(birthDateExist);
        if (birthDate != null) {
            long bDate = birthDate.getTime();
            writer.println("" + bDate);
        }

        String iaMaleString = isMale ? "yes" : "no";
        writer.println(iaMaleString);

        String isCountryExist = country == null ? "no" : "yes";
        writer.println(isCountryExist);
        if (!(country == null))
            writer.println(country.getDisplayedName());

//        writer.close();
    }

    public void load(BufferedReader reader) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String ifExist = reader.readLine();
        if ("yes".equals(ifExist))
            this.firstName = reader.readLine();

        ifExist = reader.readLine();
        if ("yes".equals(ifExist))
            this.lastName = reader.readLine();

        ifExist = reader.readLine();
        if ("yes".equals(ifExist)) {
            String bd = reader.readLine();
            long time = Long.parseLong(bd);
            this.birthDate = new Date(time);
        }

//        ifExist = reader.readLine();
//        if ("yes".equals(ifExist)) {
            String sex = reader.readLine();
            this.isMale = ("yes".equals(sex));
//        }

        ifExist = reader.readLine();
        if ("yes".equals(ifExist)) {
            String countryName = reader.readLine();
//            this.country;
            if ("Ukraine".equals(countryName))
                this.country = Country.UKRAINE;
            else
                if ("Russia".equals(countryName))
                    this.country = Country.RUSSIA;
            else
                if ("Other".equals(countryName))
                    this.country = Country.OTHER;
        }

    }
}
