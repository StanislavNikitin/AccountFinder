package ru.innopolis.finder.servlets;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

            //if there were login and email
            boolean allContained = false; //email and login exist in request params
            if (incomingParams.containsKey(IOTemplate.InputField.LOGIN) && incomingParams.containsKey(IOTemplate.InputField.MAIL)){
                allContained = true;
                //process it!
                //validation -> sending to GH manager -> ...
            }


            if (allContained) { //email and login exist in request params

                request.setAttribute("isShowingResult", "true");
                request.setAttribute("login", incomingParams.get(IOTemplate.InputField.LOGIN));
                request.setAttribute("email", incomingParams.get(IOTemplate.InputField.MAIL));

                //Demonstration of how to set session variables
                /*HttpSession session = request.getSession(true);
                if (session == null) { //create a new session
                    session = request.getSession(false);
                }

                session.setAttribute("isShowingResult", "true");
                session.setAttribute("login", incomingParams.get(IOTemplate.InputField.LOGIN));
                session.setAttribute("email", incomingParams.get(IOTemplate.InputField.MAIL));*/

            }

            //redirecting to main page
            request.getRequestDispatcher("index.jsp").forward(request, response);

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
