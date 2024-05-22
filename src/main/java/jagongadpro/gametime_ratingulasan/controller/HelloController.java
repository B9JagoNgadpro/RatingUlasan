package jagongadpro.gametime_ratingulasan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public String createHomePage(){
        return "deploySementara";
    }

}