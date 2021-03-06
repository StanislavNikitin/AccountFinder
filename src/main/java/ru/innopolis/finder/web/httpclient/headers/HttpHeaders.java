package ru.innopolis.finder.web.httpclient.headers;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 *
 * @author Leha
 */
public class HttpHeaders {

    protected static final String[] generalHeaders  = {""};
    protected static final String[][] defaultHeaders = {{"Connection", "keep-alive"}, {"User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36"}};

    public static List<Header> getDefaultHeadersList(){

        List<Header> result = new ArrayList<Header>();

        for (String[] header : defaultHeaders){
            result.add(new BasicHeader(header[0], header[1]));
        }

        return result;

    }
}
