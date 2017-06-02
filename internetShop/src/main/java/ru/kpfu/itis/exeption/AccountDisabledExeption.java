package ru.kpfu.itis.exeption;

import org.springframework.security.authentication.AccountStatusException;

/**
 * Created by habar on 04.05.2017.
 */
public class AccountDisabledExeption extends AccountStatusException {


    public AccountDisabledExeption(String msg) {
        super(msg);
    }
}
