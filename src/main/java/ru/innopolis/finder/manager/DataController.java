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
        
        FBInfo fi = new FBInfo("EAACEdEose0cBAEMjXFDBYvSh9ZCXMTHlDel2WdflVTbal1NBGOFNdIHECx17Iibv7eG35cSZCEyZB3caT5ZCb7gwYhn1StIbnP28IZBX9ZCSf1PB0h69hus5LLsaO8xGohNLc7WIl7Hw0rssUVcLduVzIOaa8QTLWc8Oepi8RKGgZDZD");
        return fi.getUsers(userName, location).toArray(new Profile[0]);
    }
}
