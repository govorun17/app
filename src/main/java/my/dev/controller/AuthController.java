package my.dev.controller;

import my.dev.controller.model.AuthModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class AuthController {

    @GetMapping("/")
    public String auth() {
        return "register";
    }

    @PostMapping("/")
    @ResponseBody
    public View register(AuthModel formMap) {
        return new RedirectView("/");
    }

}
