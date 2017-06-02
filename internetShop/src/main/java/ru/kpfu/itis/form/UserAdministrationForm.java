package ru.kpfu.itis.form;

import ru.kpfu.itis.model.enums.UserRole;

/**
 * Created by habar on 30.04.2017.
 */
public class UserAdministrationForm {

    private String username;
    private UserRole role;
    private boolean activation;
    private String email;
    private boolean delete;

    public boolean getActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public UserRole getRole() {

        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
