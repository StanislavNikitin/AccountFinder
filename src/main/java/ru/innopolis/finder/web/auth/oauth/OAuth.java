package ru.innopolis.finder.web.auth.oauth;

/**
 *
 * @author Leha
 */
public interface OAuth {
    String performAuthorization(String login, String password);
    Boolean isAuthorized(String accessToken);
}
