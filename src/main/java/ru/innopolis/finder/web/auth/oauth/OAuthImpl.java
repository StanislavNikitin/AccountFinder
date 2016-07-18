package ru.innopolis.finder.web.auth.oauth;

import ru.innopolis.finder.web.httpclient.HttpBrowser;
import ru.innopolis.finder.web.httpclient.HttpResponse;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Leha
 */
public abstract class OAuthImpl implements OAuth{

    //Все поля и методы, общие для ВСЕХ классов авторизации помещаем сюда
    private final String applicationId, scope, redirectURI, display, version, responseType; //параметры строки авторизации
    private final String oAuthURI; //домен для авторизации
    private String accessToken; //поле для ключа
    protected HttpBrowser browser; //браузер
    protected HttpResponse response; //http ответ
    protected OAuthException currException; //статус выполнения последней операции
    
    /**
     * Объект класса будет создан успешно, если обязательные параметры не null. Иначе - исключение OAuthException.
     * @param OAuthURI (обязательный) адрес для отправки OAuth запроса (в формате "oauth.domain.com/authorization", без указания протокола и указания параметров)
     * @param applicationId (обязательный) уникальный номер приложения в данной соц. сети
     * @param scope (обязательный) набор разрешений в данной соц. сети
     * @param redirectURI (обязательный) адрес отправки токена
     * @param display тип отображения окна авторизации
     * @param version версия API
     * @param responseType тип ответа: code или token
     */
    protected OAuthImpl(String OAuthURI, String applicationId, String scope, String redirectURI, String display, String version, String responseType){

        if (OAuthURI == null || applicationId == null || scope == null || redirectURI == null)
            currException =  new OAuthException("Incorrect input parameters");

        this.oAuthURI = OAuthURI;
        this.applicationId = applicationId;
        this.scope = scope;
        this.redirectURI = redirectURI;
        this.display = display;
        this.version = version;
        this.responseType = responseType;

        try {
            browser = new HttpBrowser(getRequestURLFromParams(), false);
        } catch (UnsupportedEncodingException e){
            currException = new OAuthException("Can't encode redirect url");
        }

        if (browser.getCurrentURI() == null) currException = new OAuthException("Incorrect url for performing OAuth autorization");

    }
    
    /**
     * Возвращает accessToken, хранящийся в поле класса
     * @return accessToken - ключ авторизации для соц. сети
     */
    protected String getAccessToken(){
        return this.accessToken;
    }
    
    /**
     * Формирует url для oauth запроса на авторизацию
     * @return Строка для oauth запроса
     * @throws UnsupportedEncodingException если проблемы с REDIRECT_URI
     */
    protected String getRequestURLFromParams() throws UnsupportedEncodingException{
        String result = "https://";
        result += oAuthURI + "?" + OAuthFields.createParamString(applicationId, scope, redirectURI, display, version, responseType);
        return result;
    }
    
    /**
     * Возвращает последнюю полученную ошибку. Если ошибок не было - null
     * @return 
     */
    protected OAuthException getErrorStatus(){
        return currException;
    }
    
    
    @Override
    public abstract Boolean isAuthorized(String accessToken);
    
    @Override
    public abstract String performAuthorization(String email, String password);
    
}
