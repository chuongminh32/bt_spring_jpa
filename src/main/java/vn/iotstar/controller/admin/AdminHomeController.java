package vn.iotstar.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @GetMapping("/admin/home")
    public String adminHome() {
        // Trả về view tương ứng: /views/admin/home.jsp
        // Nếu bạn dùng Thymeleaf thì chỉ cần trả về "admin/home"
        return "admin/home"; 
    }
}
		