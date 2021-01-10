package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;


@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/", "/user"})
    public String index() {
        return "UserPage";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable("id") String id) {
        long allowId = tryParse(id);
        model.addAttribute("userInfo", userService.findById(allowId));
        return "UserPage";
    }

    long tryParse(String s) {
        long res = 0;
        try {
            res = Long.parseLong(s);
        } catch (NumberFormatException ex) { /**/ }
        return res;
    }
}
