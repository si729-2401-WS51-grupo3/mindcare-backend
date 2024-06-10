package pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.aggregates;

import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;

import java.util.Objects;

public class UserA {
    private User user;

    public UserA(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserA that = (UserA) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "UserA{" +
                "user=" + user +
                '}';
    }
}