package vn.iotstar.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.entity.Book;
import vn.iotstar.entity.Rating;
import vn.iotstar.entity.User;
import vn.iotstar.service.IBookService;
import vn.iotstar.service.IRatingService;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class BooksAdminController {

    private final IBookService bookService;
    private final IRatingService ratingService;

    // ================= Câu 6: CRUD + Phân trang =================
    // Danh sách Books
    @GetMapping
    public String listBooks(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size) {
        Page<Book> list = bookService.findAll(PageRequest.of(page, size));
        model.addAttribute("books", list.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", list.getTotalPages());
        return "admin/books-list";
    }

    // Form thêm
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("book", new Book());
        return "admin/book-form";
    }

    // Lưu thêm/sửa
    @PostMapping("/save")
    public String save(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/admin/books";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "admin/book-form";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/admin/books";
    }

    // ================= Câu 4: Chi tiết + Reviews =================
    @GetMapping("/detail/{id}")
    public String bookDetail(@PathVariable("id") int id, Model model) {
        Book book = bookService.findById(id);
        List<Rating> reviews = ratingService.findByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "admin/books-detail";   // => templates/admin/books-detail.html
    }

    @PostMapping("/review")
    public String addReview(
            @RequestParam("bookId") int bookId,
            @RequestParam("content") String content,
            HttpSession session
    ) {
        User user = (User) session.getAttribute("account");
        if (user == null) {
            return "redirect:/login";
        }

        Book book = bookService.findById(bookId);

        Rating rating = new Rating(user, book, 5, content);
        ratingService.save(rating);

        return "redirect:/admin/books/detail/" + bookId;
    }
}
