package vn.iotstar.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;
import vn.iotstar.utils.Constant;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    // Hiển thị form login
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "web/login"; // templates/web/login.html
    }

    // Xử lý submit login
    @PostMapping("/login")
    public String loginSubmit(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam(value = "remember", required = false) String remember,
                              HttpSession session,
                              HttpServletResponse response,
                              Model model) {

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("alert", "Email hoặc mật khẩu không được rỗng");
            return "web/login";	
        }

        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("account", user);

            boolean isRememberMe = "on".equals(remember);
            if (isRememberMe) {
                saveRememberMe(response, email);
            }

            // có thể chuyển hướng tới waiting để phân quyền
              return "redirect:/waiting";
         
//            return "user/home";
        } else {
            model.addAttribute("alert", "Email hoặc mật khẩu không đúng");
            return "web/login";
        }
    }

    private void saveRememberMe(HttpServletResponse response, String email) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, email);
        cookie.setMaxAge(30 * 60); // 30 phút
        response.addCookie(cookie);
    }
}
