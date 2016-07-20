package ru.innopolis.finder.manager;

import ru.innopolis.finder.service.Profile;
import ru.innopolis.finder.web.socials.FBInfo;
import ru.innopolis.finder.web.socials.GitHubInfo;

/**
 * Created by user on 19.07.16.
 */
public class DataController {
    public Profile[] process(String nickname, String email) throws Exception {
        GitHubInfo gi = new GitHubInfo(email, nickname);
        if (!gi.setUser(nickname, email)) {
            return null;
        }
        String location = gi.getUserLocation();
        String userName = gi.getUserName();

        if (userName == null) {
            return null;
        }

        FBInfo fi = new FBInfo("EAACEdEose0cBAM6UNnmCXxZBX7ipGy3R06AEUiAbWk2ltOdAkaRQZCrluGmTS7cl2cLXUFa2EOT3ZCx7xfogHDA7LRa4oZCpMZC7tWHxsY2lj1V298GdlPFcKzlcFC7X8ukcFMQsaeWG9GePQxkhldW6qqAu6YKx87PdpbosZCmQZDZD");
        return fi.getUsers(userName, location).toArray(new Profile[0]);
    }
}
