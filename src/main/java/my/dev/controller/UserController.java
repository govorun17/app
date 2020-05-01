package my.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/index")
    private String index(ModelMap model) {
        model.addAttribute("str", "Hello");
        return "index";
    }

    @GetMapping("/")
    private String common(ModelMap model) {
        return "register";
    }
}