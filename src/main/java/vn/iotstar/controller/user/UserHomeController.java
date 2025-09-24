package vn.iotstar.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserHomeController {

    @GetMapping("/user/home")
    public String adminHome() {
        // Trả về view tương ứng: /views/user/home.jsp
        // Nếu bạn dùng Thymeleaf thì chỉ cần trả về "user/home"
        return "user/home"; 
    }
}
