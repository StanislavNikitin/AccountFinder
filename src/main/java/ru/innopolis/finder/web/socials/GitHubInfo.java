package ru.innopolis.finder.web.socials;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.innopolis.finder.web.httpclient.HttpBrowser;
import ru.innopolis.finder.web.httpclient.HttpResponse;

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

    private static final String USERS_URI = "https://api.github.com/users/";
    private String login, email;

    private HttpBrowser browser;
    private HttpResponse response;

    private String jsonContent;
    private JSONParser jsonParser;


    public GitHubInfo(){
        this(null, null);
    }

    public GitHubInfo(String login, String email){
        browser = new HttpBrowser(null, false);
        jsonContent = null;
        response = null;
        jsonParser = new JSONParser();

        this.login = login;
        this.email = email;
    }

    /** @return field dataType about user from GH or null, if user error happens
     */
    private String getUserData(UserData dataType){

        if (jsonContent == null){
            if (!userExists()) return null;
        }

        String userData = null;
        try {

            JSONObject json = (JSONObject)jsonParser.parse(jsonContent);
            if (dataType == UserData.LOCATION){
                userData = (String)json.get("location");
            } else if (dataType == UserData.NAME){
                userData = (String)json.get("name");
            }

        } catch (ParseException e){
            e.printStackTrace();
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

    /** @return true if user info from GH exists, false otherwise
     *  store user info in jsonContent, or set jsonContent to null if info doesn't exists
     */
    private boolean userExists() {

        if (this.login == null) return false;

        browser.setCurrentURI(USERS_URI + login); //set URI for request
        response = browser.sendGetRequest();

        if (null != response && response.getResponseCode() == 200){ //request success
            if (response.hasContent()){ //response not empty
                Document htmlContent = Jsoup.parse(response.getResponseContet());
                jsonContent = String.valueOf(htmlContent.select("body").text());
                if (jsonContent != null){ //user exists
                    return true;
                } else {
                    jsonContent = null;
                }
            }
        }

        jsonContent = null;
        return false;

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
