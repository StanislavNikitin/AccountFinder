package ru.innopolis.finder.templates;

/**
 * Created by Leha on 16-Jul-16.
 */
public class IOTemplate {

    //Fields count in InputField and fieldsNames must be same!
    private static final int FIELDS_COUNT = 3;

    public enum InputField {
        MAIL, LOGIN, ACTION;
        public static InputField[] getInputFields(){
            InputField[] values = new InputField[3];
            values[0] = MAIL;
            values[1] = LOGIN;
            values[2] = ACTION;
            return values;
        }
    }

    private static String[] fieldsNames = {"email", "login", "action"};

    public static String[] getFieldsNames(){
        return fieldsNames;
    }

}
