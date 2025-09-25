//package vn.iotstar.controller.user;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import lombok.RequiredArgsConstructor;
//import vn.iotstar.service.IBookService;
//
//@Controller
//@RequestMapping("/user") // localhost:8080/user
//@RequiredArgsConstructor
//public class BooksUserController {
//
//    private final IBookService bookService;
//
//    @GetMapping("/books") // localhost:8080/user/books
//    public String listBooks(Model model) {
//        model.addAttribute("listBook", bookService.findAll());
//        return "user/books-list"; // template/user/books-list.html
//    }
//}
