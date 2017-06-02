package ru.kpfu.itis.exeption;

import org.springframework.security.authentication.AccountStatusException;

/**
 * Created by habar on 04.05.2017.
 */
public class AccountAlreadyExistExeption extends AccountStatusException {


    public AccountAlreadyExistExeption(String msg) {
        super(msg);
    }
}
