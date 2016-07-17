package ru.finder.templates;

/**
 * Created by Leha on 16-Jul-16.
 */
public class IOTemplate {

    //Fields count in InputField and fieldsNames must be same!
    private static final int FIELDS_COUNT = 2;

    public enum InputField {
        MAIL, LOGIN;
        public static InputField[] getInputFields(){
            InputField[] values = new InputField[2];
            values[0] = MAIL;
            values[1] = LOGIN;
            return values;
        }
    }

    private static String[] fieldsNames = {"email", "login"};

    public static String[] getFieldsNames(){
        return fieldsNames;
    }

}
