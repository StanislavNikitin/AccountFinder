package ru.innopolis.finder.data;

import ru.innopolis.finder.service.Profile;

/**
 * Created by Leha on 18-Jul-16.
 */
public class IOManager {

    private DataController dc;
    private String login, email;

    public IOManager(){
        login = "";
        email = "";
        dc = new DataController();
    }


    public boolean checkData(String login, String email){
        if(!login.isEmpty() && !email.isEmpty()){
            this.login = login;
            this.email = email;
            return true;
        }
        return false;
    }

    public Profile[] getFromFB(String fbToken) throws Exception{
        Profile[] profilesList = null;

        if(!fbToken.isEmpty()){
            try {
                profilesList = dc.process(login, email, fbToken);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }

        return profilesList;
    }

}
