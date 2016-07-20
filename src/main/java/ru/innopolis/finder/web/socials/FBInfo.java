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

    private static String accesToken = "EAACEdEose0cBAM6UNnmCXxZBX7ipGy3R06AEUiAbWk2ltOdAkaRQZCrluGmTS7cl2cLXUFa2EOT3ZCx7xfogHDA7LRa4oZCpMZC7tWHxsY2lj1V298GdlPFcKzlcFC7X8ukcFMQsaeWG9GePQxkhldW6qqAu6YKx87PdpbosZCmQZDZD";
    private static final String searchFields = "link,location,cover,name,id,hometown";
    private FacebookClient fbClient;

    public FBInfo(String accesToken) {
        if (accesToken != null)
            this.accesToken = accesToken;
    }

    public List<Profile> getUsers(String userName, String userLocation) {

        fbClient = new DefaultFacebookClient(accesToken, Version.VERSION_2_6);
        Connection<User> searchResults = fbClient.fetchConnection("search", User.class,
                Parameter.with("q", userName), Parameter.with("type", "user"),
                Parameter.with("fields", searchFields));

        List<User> users = searchResults.getData();
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
