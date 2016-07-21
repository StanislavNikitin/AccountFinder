package ru.innopolis.finder.templates;

/**
 * Created by Leha on 16-Jul-16.
 */
public class IOTemplate {

    //Fields count in InputField and fieldsNames must be same!
    private static final int FIELDS_COUNT = 4;

    public enum InputField {
        MAIL, LOGIN, ACTION, FACEBOOK_CODE;
        public static InputField[] getInputFields(){
            InputField[] values = new InputField[FIELDS_COUNT];
            values[0] = MAIL;
            values[1] = LOGIN;
            values[2] = ACTION;
            values[3] = FACEBOOK_CODE;
            return values;
        }
    }

    private static String[] fieldsNames = {"email", "login", "action", "code"};

    public static String[] getFieldsNames(){
        return fieldsNames;
    }

}
