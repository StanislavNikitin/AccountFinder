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

    private boolean validateData(){
        return true;
    }

    public Profile[] processData(String email, String login) throws NotValidInputDataException{
        Profile[] profilesList = null;
        if(!login.isEmpty() && !email.isEmpty()){
            //validate email
            DataController dc = new DataController();
            try {
                profilesList = dc.process(login, email);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return profilesList;
    }

}
