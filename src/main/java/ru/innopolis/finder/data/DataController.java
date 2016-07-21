package ru.innopolis.finder.data;

import ru.innopolis.finder.service.Profile;
import ru.innopolis.finder.web.socials.FBInfo;
import ru.innopolis.finder.web.socials.GitHubInfo;

/**
 * Created by user on 19.07.16.
 */
public class DataController {
    public Profile[] process(String nickname, String email, String fbToken) throws Exception {

        GitHubInfo gi = new GitHubInfo(email, nickname);

        if (!gi.setUser(nickname, email)) {
            throw new Exception("User doesn't exists on GitHub");
        }

        String location = gi.getUserLocation();
        String userName = gi.getUserName();

        if (userName == null) {
            throw new Exception("User on GitHub doesn't have fields 'Name' and 'Surname'");
        }


        FBInfo fi = new FBInfo(fbToken);
        return fi.getUsers(userName, location).toArray(new Profile[0]);
    }
}
