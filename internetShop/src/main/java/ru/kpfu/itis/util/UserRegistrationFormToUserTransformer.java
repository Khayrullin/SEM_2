package ru.kpfu.itis.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;

public class UserRegistrationFormToUserTransformer {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User transform(UserRegistrationForm form) {
        if (form == null) {
            return null;
        }
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setRole(UserRole.ROLE_BUYER);
        user.setActivation(false);
        user.setPassword(encoder.encode(form.getPassword()));
        return user;
    }
}
