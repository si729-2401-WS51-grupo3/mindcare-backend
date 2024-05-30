package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.valueobjects;

public enum Email {
    WORK("work@example.com"),
    PERSONAL("personal@example.com"),
    SUPPORT("support@example.com");

    private final String email;

    Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return email;
    }
}


