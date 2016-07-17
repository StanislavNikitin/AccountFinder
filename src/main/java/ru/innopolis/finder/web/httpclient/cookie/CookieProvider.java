package ru.innopolis.finder.web.httpclient.cookie;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.cookie.NetscapeDraftSpec;
import org.apache.http.protocol.HttpContext;

/**
 *
 * @author Leha
 */
public class CookieProvider implements org.apache.http.cookie.CookieSpecProvider {

    public static String PROVIDER_NAME = "vk";
    
    @Override
    public CookieSpec create(HttpContext hc) {
        return new NetscapeDraftSpec(CookieParser.DATE_PATTERNS);
    }
    
}
