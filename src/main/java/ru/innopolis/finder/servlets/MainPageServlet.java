package ru.innopolis.finder.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.innopolis.finder.data.IOManager;
import ru.innopolis.finder.service.Profile;
import ru.innopolis.finder.templates.IOTemplate;

/**
 * Created by Leha on 16-Jul-16.
 */
public class MainPageServlet extends HttpServlet {

    //Here request is processing, and after it's execution response will be returned
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    if (request.getMethod().equalsIgnoreCase("get")){//in case of GET request

        response.setContentType("text/html;charset=UTF-8");

        //Checking for parameters located in class IOTemplate in parameters list that were coming from user with HTTP request
        Map<IOTemplate.InputField, String> incomingParams = new HashMap<>();
        String[] fieldsNames = IOTemplate.getFieldsNames();
        int i = 0;
        String currParameter;

        //Finding in request for parameters, defined in IOTemplate
        for (IOTemplate.InputField field : IOTemplate.InputField.getInputFields()){

            currParameter = request.getParameter(fieldsNames[i]);
            if (currParameter != null) {
                incomingParams.put(field, currParameter);
            }
            i++;

        }

        if (incomingParams.containsKey(IOTemplate.InputField.ACTION)) {

            if (incomingParams.get(IOTemplate.InputField.ACTION).equalsIgnoreCase("find")) {

                JSONObject jsonResult = new JSONObject(),
                           jsonSN = new JSONObject();
                JSONArray profilesArray = new JSONArray();
                String processingException = null;
                boolean isSuccess = false;
                int profilesCount = 0;

                String login = incomingParams.get(IOTemplate.InputField.LOGIN);
                String email = incomingParams.get(IOTemplate.InputField.MAIL);
                String fbToken = incomingParams.get(IOTemplate.InputField.FACEBOOK_TOKEN);

                if (login != null && email != null) {//if both fields exists in the request

                    IOManager ioManager = new IOManager();
                    Profile[] profiles = null;
                    if (ioManager.checkData(login, email)) { //correct input data

                        //Process FB
                        if (fbToken != null) {
                            try {
                                profiles = ioManager.getFromFB(fbToken);
                                isSuccess = true;
                            } catch (Exception e) {
                                processingException = e.getMessage();
                                e.printStackTrace();
                            }
                        } else {
                            processingException = "Please, enter facebook token";
                        }
                        if (profiles != null) {
                            for (int index = 0; index < profiles.length; index++) {
                                Profile p = profiles[index];
                                profilesCount++;
                                profilesArray.add(p);
                            }
                            jsonSN.put("facebook", profilesArray);
                        }

                    } else {
                        processingException = "Incorrect input data";
                    }
                }  else {//end of valid input data condition
                    processingException = "Please, enter login and email";
                }

                if (processingException != null){
                    jsonResult.put("error", processingException);
                } else {
                    jsonResult.put("error", "");
                }
                jsonResult.put("count", new Integer(profilesCount));
                jsonResult.put("success", isSuccess);
                jsonResult.put("data", jsonSN);
                response.getWriter().write(jsonResult.toJSONString());

            } //end of "parameter 'action' equals 'find'"

        }
        else { //without action - forward to main page
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    } else if (request.getMethod().equalsIgnoreCase("post")){ //in case of POST request
    }

}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet override methods. Click on the + sign on the left to edit the code.">
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MainPageServlet.class.getName()).log(Priority.ERROR, null, ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(MainPageServlet.class.getName()).log(Priority.ERROR, null, ex);
        }
    }
    //</editor-fold>
}
