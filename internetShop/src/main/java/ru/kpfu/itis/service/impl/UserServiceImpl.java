package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.exeption.AccountAlreadyExistExeption;
import ru.kpfu.itis.form.UserAdministrationForm;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.model.Token;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.registration.MailMail;
import ru.kpfu.itis.repository.TokenRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.UserRegistrationFormToUserTransformer;

import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void saveNewUser(UserRegistrationForm form) {
        User user = UserRegistrationFormToUserTransformer.transform(form);

        if(userRepository.findOneByEmail(form.getEmail()) != null){
            throw new AccountAlreadyExistExeption("Account Already Exists!");
        }

        Token tk = new Token();
        tk.setUuid(java.util.UUID.randomUUID().toString());
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date()); // Now use today date.
        c.add(Calendar.DATE, 3);
        java.util.Date now_plus_5_days = c.getTime();
        tk.setDeleteDate(now_plus_5_days);
        tk.setUser(user);
        userRepository.save(user);
        tokenRepository.save(tk);



        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/Spring-Mail.xml");

        MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("from@no-spam.com",
                form.getEmail(),
                "Confirm the Registration.",
                "http://localhost:8080/confirm?tokenUuid="+tk.getUuid());



        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Secured({ "ROLE_ADMIN" })
    @Override
    public void securedMethod(UserAdministrationForm form) {
        if(form.getDelete()){
            userRepository.delete(userRepository.findOneByUsername(form.getUsername()));
        }else{
            User user = userRepository.findOneByUsername(form.getUsername());
            user.setActivation(form.getActivation());
            user.setRole(form.getRole());
            userRepository.save(user);
        }
    }

    @Override
    public boolean activateUser(String token) {
        Token token1 = tokenRepository.findOneByUuid(token);
        if(token1 == null){
            return false;
        }
        User user = userRepository.findOne(token1.getUser().getId());
        tokenRepository.delete(token1);
        user.setActivation(true);
        userRepository.save(user);
        return true;
    }


}
