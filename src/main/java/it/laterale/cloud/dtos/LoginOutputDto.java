package it.laterale.cloud.dtos;

import java.io.Serializable;

/**
 * The type Login output dto.
 */
public class LoginOutputDto implements Serializable {

    private String token;

    /**
     * Gets token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
