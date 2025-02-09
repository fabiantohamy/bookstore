package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.CategoryRepository;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId) {
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/editbook/{id}", method = RequestMethod.GET)
    public String editBookForm(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }

    @RequestMapping(value = "/editbook", method = RequestMethod.POST)
    public String editBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }
}