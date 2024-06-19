package bg.softuni.planner.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
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

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public void login(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public boolean isLoggedIn() {
        return this.username != null;
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }
}
