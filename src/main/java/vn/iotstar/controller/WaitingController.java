package vn.iotstar.controller;

import vn.iotstar.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WaitingController {

    @GetMapping("/waiting")
    public String waiting(HttpSession session) {
        User u = (User) session.getAttribute("account");

        if (u != null) {
            if (Boolean.FALSE.equals(u.getIsAdmin())) {
            	// Thymeleaf sẽ tự động nhúng vào layout/user.html
                return "user/home";   // Layout user
            } else {
                return "admin/home";  // Layout admin
            }
        } else {
            return "login";           // Quay về trang login
        }
    }
}

