package ru.innopolis.finder.service;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * Created by Leha on 18-Jul-16.
 */
public class Profile implements JSONAware{

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

    /**
     * @return JSON text
     */
    @Override
    public String toJSONString() {
        StringBuffer sb = new StringBuffer();

        sb.append("{");

        sb.append("\"" + JSONObject.escape("name") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(name) + "\"");

        sb.append(",");

        sb.append("\"" + JSONObject.escape("link") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(link) + "\"");

        sb.append(",");

        sb.append("\"" + JSONObject.escape("cover") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(cover) + "\"");

        sb.append("}");

        return sb.toString();
    }
}
