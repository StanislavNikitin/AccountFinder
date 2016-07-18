package ru.innopolis.finder.handlers.validators;

/**
 * Created by Alexander Laptev on 17.07.2016.
 */
class Country {
    private String code;
    private String name;
    private String nativeName;

    String getCode() {
        return code;
    }

    String getName() {
        return name;
    }

    String getNativeName() {
        return nativeName;
    }

    Country (String Code, String Name, String NativeName){
        code = Code;
        name = Name;
        nativeName = NativeName;
    }
}
