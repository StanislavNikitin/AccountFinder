package ru.innopolis.finder.web.socials;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import java.io.IOException;

/**
 * Created by Leha on 19-Jul-16.
 */
public class GitHubInfo {

    //for adding new user field, follow these steps:
    //1) Add field name to enum UserData
    //2) Add if statement in private method getUserData(..)
    //3) Create getter like public String getUserLocation();

    private enum UserData{
        LOCATION, NAME;
    }

    private String login, email;
    private GHUser user;
    private GitHub gitHub;

    /**
     *
     * @param userEmail email in the correct form
     * @param userLogin user login on GH
     * @throws IOException if can't find user on GH (connection trouble of user doesn't exists)
     */
    public GitHubInfo(String userLogin, String userEmail) throws IOException {

        if (!setUser(userLogin, userEmail)){
            throw new IOException("User with login " + userLogin + " and email " + userEmail + " can't be founded on GH");
        }

    }

    private String getUserData(UserData dataType){

        if (user == null){
            return null;
        }

        String userData = null;
        try {

            if (dataType == UserData.LOCATION){
                userData = user.getLocation();
            } else if (dataType == UserData.NAME){
                userData = user.getName();
            }

        } catch (IOException e){
            userData = null;
        }
        return userData;

    }

    /** @return null if can't find that field, otherwise return user location from GH
     */
    public String getUserLocation(){
        return getUserData(UserData.LOCATION);
    }

    /** @return null if can't find that field, otherwise return user name from GH
     */
    public String getUserName() {
        return getUserData(UserData.NAME);
    }

    private boolean userExists() {

        try {
            gitHub = GitHub.connect();
            user = gitHub.getUser(login);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param userEmail email in the correct form
     * @param userLogin user login on GH
     * @return true if user exists on GH, false if doesn't exists or connection problems
     */
    public boolean setUser(String userLogin, String userEmail){

        this.login = userLogin;
        this.email = userEmail;
        return userExists();

    }
}
