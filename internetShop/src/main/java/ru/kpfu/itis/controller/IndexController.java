package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.UserAdministrationForm;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.service.UserService;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    UserService userService;
    @Autowired
    private MessageSource messages;

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "index";
    }


    @RequestMapping(value = "/forbidden")
    public String getForbiddenPage() {
        return "access-deny";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userform", new UserRegistrationForm());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userform") @Valid UserRegistrationForm form) {

        try {
            userService.saveNewUser(form);
        } catch (Exception e){

        }


        return "redirect:/";
    }


    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "adminPage";
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public String confirmnation(@ModelAttribute("tokenUuid") String token) {
        if (userService.activateUser(token)) {
            return "successReg";
        } else {
            return "access-deny";
        }

    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.POST)
    public String changeUser(@ModelAttribute("userAdministrationForm") UserAdministrationForm form) {
        userService.securedMethod(form);
        return "redirect:/adminPage";
    }


}

