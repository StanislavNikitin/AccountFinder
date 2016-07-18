package ru.innopolis.finder.handlers.validators;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexander Laptev on 17.07.2016.
 */
public class CountryValidator {

    private LinkedList<Country> Countries = new LinkedList<Country>();
    private Map<String, String> languagesMap = new TreeMap<String, String>();

    private void initLanguageMap() {
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale obj : locales) {
            if ((obj.getDisplayCountry() != null) && (!"".equals(obj.getDisplayCountry()))) {
                languagesMap.put(obj.getCountry(), obj.getLanguage());
            }
        }
    }

    private void initCountryList() {
        initLanguageMap();
        String[] IsoCountries = Locale.getISOCountries();
        for (String countryCode : IsoCountries) {
            Locale locale = new Locale("", countryCode);
            if (languagesMap.get(countryCode) != null){
                Locale nativeLocale = new Locale(languagesMap.get(countryCode), countryCode);
                Countries.add(new Country(locale.getCountry(), locale.getDisplayCountry(Locale.ENGLISH),
                        nativeLocale.getDisplayCountry(nativeLocale)));
            }
            else {
                Countries.add(new Country(locale.getCountry(), locale.getDisplayCountry(Locale.ENGLISH), ""));
            }
        }
    }

    CountryValidator (){
        initCountryList();
    }

    public String [] Validate (String line) throws Exception{
        for (Country country : Countries){
            if (line.equals(country.getCode()) ||
                    line.equals(country.getName()) ||
                    line.equals(country.getNativeName())){
                String[] result = { country.getCode(), country.getName(), country.getNativeName()};
                return result;
            }
        }
        throw new Exception("Not a country name.");
    }
}
