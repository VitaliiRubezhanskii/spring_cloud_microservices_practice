package ua.rubezhanskii.lesson1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public  String getOnePager(){
        return "employeePage";
    }
}
