package ru.innopolis.finder.service;

/**
 * Created by Leha on 18-Jul-16.
 */
public class Profile {

    private String name = "";
    private String cover = "";
    private String link = "";

    public Profile(String name, String cover, String link) {
        this.setName(name);
        this.setCover(cover);
        this.setLink(link);
    }

    public Profile() {
        this("", "", "");
    }

    public String getLink() {
        return link;
    }

    public String getCover() {
        return cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null)
            this.name = name;
    }

    public void setCover(String cover) {
        if (cover != null)
            this.cover = cover;
    }

    public void setLink(String link) {
        if (link != null)
            this.link = link;
    }
}
