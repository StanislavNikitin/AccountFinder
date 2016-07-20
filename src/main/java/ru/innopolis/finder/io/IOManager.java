package ru.innopolis.finder.io;

import ru.innopolis.finder.manager.DataController;
import ru.innopolis.finder.service.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leha on 18-Jul-16.
 */
public class IOManager {

    private DataController dc;

    public IOManager(){
        dc = new DataController();
    }


    public Profile[] processData(String login, String email, String fbToken) throws NotValidInputDataException{
        Profile[] profilesList = null;
        if(!login.isEmpty() && !email.isEmpty() && !fbToken.isEmpty()){
            try {
                profilesList = dc.process(login, email, fbToken);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return profilesList;
    }

}
