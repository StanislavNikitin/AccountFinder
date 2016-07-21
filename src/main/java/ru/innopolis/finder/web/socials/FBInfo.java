package ru.innopolis.finder.web.socials;

import com.restfb.*;
import com.restfb.types.User;
import ru.innopolis.finder.service.Profile;
import ru.innopolis.finder.web.httpclient.HttpBrowser;
import ru.innopolis.finder.web.httpclient.HttpResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leha on 19-Jul-16.
 */
public class FBInfo {

    private static final String searchFields = "link,location,cover,name,id,hometown";
    private static final String appId = "720825914723269", appSecret = "78a0ddc89a15a5b9dbea0f4c4267c27d",
            redirect = "http://localhost:8080/Finder/index";
    private String accesToken = null;
    private FacebookClient fbClient;
    

    public static boolean checkToken(String accessToken){
        try {
            FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_6);
            fbClient.fetchConnection("search", User.class, Parameter.with("q", "Alexey"), Parameter.with("type", "user"));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String verifyToken(String code){

        String tokenURL = "https://graph.facebook.com/oauth/access_token?" +
                "client_id=" + appId + "&redirect_uri=" + redirect +
                "&client_secret=" + appSecret + "&code=" + code;

        HttpBrowser browser = new HttpBrowser(tokenURL, true);
        HttpResponse response = browser.sendGetRequest();
        String responseContent = response.getResponseContet();
        String accessToken = null;
        try {
            accessToken = responseContent.split("=")[1].split("&")[0];
        } catch (Exception e){
            e.printStackTrace();
        }
        return accessToken;

    }

    public FBInfo(String accesToken) {
        if (accesToken != null)
            this.accesToken = accesToken;
    }

    public List<Profile> getUsers(String userName, String userLocation) throws Exception {

        Connection<User> searchResults = null;
        try {
            fbClient = new DefaultFacebookClient(accesToken, Version.VERSION_2_6);
            searchResults = fbClient.fetchConnection("search", User.class,
                    Parameter.with("q", userName), Parameter.with("type", "user"),
                    Parameter.with("fields", searchFields));
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception("Can't recieve data from Facebook: check your access token.");
        }

        List<User> users = (searchResults == null) ? null: searchResults.getData();
        List<Profile> persons = new ArrayList<Profile>();
        if (users == null) return persons;

        for (User user : users) {
            if (user.getLink() != null) {

                Profile person = new Profile();
                person.setLink(user.getLink());

                if (user.getName() != null) {
                    person.setName(user.getName());
                }
                if (user.getCover() != null) {
                    person.setCover(user.getCover().getSource());
                }

                persons.add(person);
            }
        }

        return persons;
    }
}
