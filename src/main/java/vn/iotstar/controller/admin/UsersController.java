package vn.iotstar.controller.admin;

import vn.iotstar.entity.User;
import vn.iotstar.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UsersController {

    private final IUserService userService;

    // Danh sách user
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> list = userService.findAll();
        model.addAttribute("listUser", list);
        return "admin/user-list"; // => templates/admin/user-list.html
    }

    // Form thêm
    @GetMapping("/user/add")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/user-add"; // => templates/admin/user-add.html
    }

    // Thêm mới
    @PostMapping("/user/insert")
    public String insertUser(
            @ModelAttribute User user,
            @RequestParam("avatarFile") MultipartFile avatarFile
    ) throws IOException {

        String avatarFileName = handleUpload(avatarFile);
        user.setAvatar(avatarFileName != null ? avatarFileName : "default-avatar.jpg");

        userService.insert(user);
        return "redirect:/admin/users";
    }

    // Form sửa
    @GetMapping("/user/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "admin/user-edit";
    }

    // Cập nhật
    @PostMapping("/user/update")
    public String updateUser(
            @ModelAttribute User user,
            @RequestParam("avatarFile") MultipartFile avatarFile,
            @RequestParam("oldAvatar") String oldAvatar
    ) throws IOException {

        String newAvatar = handleUpload(avatarFile);
        user.setAvatar(newAvatar != null ? newAvatar : oldAvatar);

        userService.update(user);
        return "redirect:/admin/users";
    }

    // Xóa
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

 // Xử lý upload file
    private String handleUpload(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();

            // Lưu file vào static/uploads (nằm trong target/classes/static/uploads)
            String uploadDir = new File("target/classes/static/uploads").getAbsolutePath();

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }	

            String savedName = System.currentTimeMillis() + "_" + fileName;
            file.transferTo(new File(dir, savedName));

            return savedName; // chỉ lưu tên file, khi hiển thị thì src="/uploads/{savedName}"
        }
        return null;
    }

}
