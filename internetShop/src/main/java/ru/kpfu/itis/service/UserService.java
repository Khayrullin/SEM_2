package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.UserAdministrationForm;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.User;

import java.util.List;

@Service
public interface UserService {

    void saveNewUser(UserRegistrationForm form);

    List<User> getAllUsers();

    void securedMethod(UserAdministrationForm form);

    boolean activateUser(String token);




}
