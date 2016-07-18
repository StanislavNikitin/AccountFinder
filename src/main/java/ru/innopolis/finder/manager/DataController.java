package ru.innopolis.finder.manager;

import ru.innopolis.finder.service.Profile;

/**
 * Created by user on 19.07.16.
 */
public class DataController {
    public Profile[] process(String nickname, String email) {
        return new Profile[] { new Profile("name1", "surname1", "http://link1"),
                new Profile("name2", "surname2", "http://link2"),
                new Profile("name3", "surname3", "http://link3")};
    }
}
