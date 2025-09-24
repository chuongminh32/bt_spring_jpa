package vn.iotstar.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.service.IUserService;
import vn.iotstar.utils.Constant;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private IUserService service;

    @GetMapping
    public String showRegister(HttpSession session) {
        // Nếu đã login thì chuyển sang admin/home
//        if (session != null && session.getAttribute("account") != null) {
//            return "redirect:/admin/home";
//        }

        // Trả về trang đăng ký
        return Constant.REGISTER; // ví dụ: "register" => views/register.jsp hoặc register.html
    }

    @PostMapping
    public String doRegister(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("fullname") String fullname,
            @RequestParam(value = "phone", required = false) String phoneStr,
            Model model
    ) {
        String alertMsg = "";

        // Kiểm tra email tồn tại
        if (service.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại!";
            model.addAttribute("alert", alertMsg);
            return Constant.REGISTER;
        }

        // Kiểm tra phone hợp lệ
        Integer phone = null;
        try {
            if (phoneStr != null && !phoneStr.isEmpty()) {
                phone = Integer.parseInt(phoneStr);
            }
        } catch (NumberFormatException e) {
            alertMsg = "Số điện thoại không hợp lệ!";
            model.addAttribute("alert", alertMsg);
            return Constant.REGISTER;
        }

        if (phone != null && service.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại!";
            model.addAttribute("alert", alertMsg);
            return Constant.REGISTER;
        }

        // Thực hiện đăng ký
        boolean isSuccess = service.register(email, password, fullname, phone);

        if (isSuccess) {
            return "redirect:/login";
        } else {
            alertMsg = "System error! Vui lòng thử lại sau.";
            model.addAttribute("alert", alertMsg);
            return Constant.REGISTER;
        }
    }
}
