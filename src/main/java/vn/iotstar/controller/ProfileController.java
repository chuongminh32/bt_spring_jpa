package vn.iotstar.controller;

import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
@RequestMapping // không fix cứng /user hay /admin
public class ProfileController {

    @Autowired
    private IUserService service;

    // 👉 Hiển thị trang profile
    @GetMapping({"/user/profile", "/admin/profile"})
    public String profile(HttpSession session, Model model) {
        User account = (User) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        model.addAttribute("account", account);

        // Nếu là admin => view admin/profile.html
        if (account.getIsAdmin()) {
            return "admin/profile";
        }
        // Ngược lại => view user/profile.html
        return "user/profile";
    }

    // 👉 Xử lý cập nhật
    @PostMapping({"/user/profile", "/admin/profile"})
    public String updateProfile(
            @RequestParam("fullName") String fullName,
            @RequestParam(value = "phone", required = false) int phone,
            @RequestParam(value = "oldPassword", required = false) String oldPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
            HttpSession session,
            Model model
    ) throws IOException {

        User account = (User) session.getAttribute("account");
        if (account == null) {
            return "redirect:/login";
        }

        // Upload avatar nếu có
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" +
                    Paths.get(avatarFile.getOriginalFilename()).getFileName().toString();

            // Lưu file vào static/uploads (nằm trong target/classes/static/uploads)
            String uploadDir = new File("target/classes/static/uploads").getAbsolutePath();
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            avatarFile.transferTo(new File(dir, fileName));
            account.setAvatar(fileName);
        }

        // Cập nhật thông tin
        account.setFullName(fullName);
        account.setPhone(phone);

        // Đổi mật khẩu
        boolean changePwd = false;
        if (oldPassword != null && !oldPassword.isEmpty()) {
            if (!oldPassword.equals(account.getPassword())) {
                model.addAttribute("error", "Mật khẩu hiện tại không đúng!");
                model.addAttribute("account", account);
                return account.getIsAdmin() ? "admin/profile" : "user/profile";
            }
            if (newPassword == null || !newPassword.equals(confirmPassword)) {
                model.addAttribute("error", "Xác nhận mật khẩu mới không khớp!");
                model.addAttribute("account", account);
                return account.getIsAdmin() ? "admin/profile" : "user/profile";
            }
            account.setPassword(newPassword);
            changePwd = true;
        }

        // Gọi service update
        boolean updated = service.updatePwd(account, changePwd);

        if (updated) {
            session.setAttribute("account", account);
            model.addAttribute("success", "Cập nhật hồ sơ thành công!");
        } else {
            model.addAttribute("error", "Có lỗi xảy ra khi cập nhật hồ sơ!");
        }

        model.addAttribute("account", account);

        // 👉 Redirect lại đúng layout
        return "redirect:" + (account.getIsAdmin() ? "/admin/profile" : "/user/profile");
    }
}
