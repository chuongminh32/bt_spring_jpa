package vn.iotstar.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa account khỏi session
        session.invalidate(); // hủy luôn toàn bộ session
        // Redirect về trang login
        return "redirect:/login";
    }
}
