package ru.innopolis.finder.web.socials;

import com.restfb.*;
import com.restfb.types.User;
import ru.innopolis.finder.service.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leha on 19-Jul-16.
 */
public class FBInfo {

    private static final String searchFields = "link,location,cover,name,id,hometown";
    private String accesToken = null;
    private FacebookClient fbClient;
    

    public static boolean checkToken(String accessToken){
        try {
            FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_6);
            fbClient.fetchConnection("search", User.class,
                    Parameter.with("q", "Alexey"), Parameter.with("type", "user"),
                    Parameter.with("fields", searchFields));
            return true;
        } catch (Exception e){
            return false;
        }
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
