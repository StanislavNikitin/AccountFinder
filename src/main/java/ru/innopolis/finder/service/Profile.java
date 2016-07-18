package ru.innopolis.finder.service;

/**
 * Created by Leha on 18-Jul-16.
 */
public class Profile {

    private String name;
    private String surname;
    private String link;

    public Profile(String name, String surname, String link) {

        this.name = name;
        this.surname = surname;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
