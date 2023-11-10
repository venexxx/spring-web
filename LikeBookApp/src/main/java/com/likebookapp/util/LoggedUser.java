package com.likebookapp.util;




import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class LoggedUser {

    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLogged() {
        return this.username != null && this.id != null;
    }

    public void reset() {
        this.username = null;
    }
}

