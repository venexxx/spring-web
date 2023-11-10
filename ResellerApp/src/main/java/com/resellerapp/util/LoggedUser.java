package com.resellerapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class LoggedUser {

    private String userName;

    private Boolean Logged;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean isLogged() {
        return Logged;
    }

    public void setLogged(Boolean logged) {
        Logged = logged;
    }
}
