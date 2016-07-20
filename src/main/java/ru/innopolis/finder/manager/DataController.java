package ru.innopolis.finder.manager;

import ru.innopolis.finder.handlers.validators.CountryValidator;
import ru.innopolis.finder.service.Profile;
import ru.innopolis.finder.web.socials.GitHubInfo;

import java.io.IOException;
import com.restfb.*;

/**
 * Created by user on 19.07.16.
 */
public class DataController {
    public Profile[] process(String nickname, String email) throws Exception {
        Profile[] result = null;
        GitHubInfo gi = new GitHubInfo(email, nickname);
        if (!gi.setUser(nickname, email)) {
            return null;
        }
        String location = gi.getUserLocation();
        String userName = gi.getUserName();

        CountryValidator cv = new CountryValidator();
        String[] countryInfo;
        countryInfo = cv.validate(location);

        if (countryInfo == null) {
            return null;
        }

        String countryCode = countryInfo[0];
        String countryName = countryInfo[1];
        String countryNativeName = countryInfo[2];

        //TODO: send data to Facebook module

        return new Profile[] { new Profile("name1", "surname1", "http://link1"),
                new Profile("name2", "surname2", "http://link2"),
                new Profile("name3", "surname3", "http://link3")};
    }
}
