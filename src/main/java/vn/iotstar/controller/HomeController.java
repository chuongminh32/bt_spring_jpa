package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Nếu cần truyền dữ liệu từ Service
        // model.addAttribute("users", userService.findAll());
        return "web/home"; 
        // Spring sẽ render: src/main/resources/templates/web/home.html
    }
}
