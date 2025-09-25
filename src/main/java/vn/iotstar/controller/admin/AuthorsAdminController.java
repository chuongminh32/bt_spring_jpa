package vn.iotstar.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.entity.Author;
import vn.iotstar.service.IAuthorService;

@Controller
@RequestMapping("/admin/authors")
@RequiredArgsConstructor
public class AuthorsAdminController {

    private final IAuthorService authorService;

    // Danh sách + phân trang
    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Page<Author> authors = authorService.findAll(PageRequest.of(page, size));
        model.addAttribute("authors", authors.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", authors.getTotalPages());
        return "admin/authors-list";
    }

    // Form thêm
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("author", new Author());
        return "admin/author-form";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "admin/author-form";
    }

    // Lưu
    @PostMapping("/save")
    public String save(@ModelAttribute Author author) {
        authorService.save(author);
        return "redirect:/admin/authors";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        authorService.delete(id);
        return "redirect:/admin/authors";
    }
}
