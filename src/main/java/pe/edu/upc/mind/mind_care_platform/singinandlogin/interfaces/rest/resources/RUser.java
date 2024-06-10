package pe.edu.upc.mind.mind_care_platform.singinandlogin.interfaces.rest.resources;

import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;

public class RUser {
    private Long id;
    private String username;
    private String email;

    public RUser() {}

    public RUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}