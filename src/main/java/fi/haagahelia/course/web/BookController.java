package fi.haagahelia.course.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.course.domain.Student;
import fi.haagahelia.course.domain.StudentRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;

@Controller
public class BookController {
	
	private BookRepository repository; 
    
    public BookController(BookRepository repository) {
        this.repository = repository;
    }
    @RequestMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
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
	
    @RequestMapping(value= {"/", "/studentlist"})
    public String studentList(Model model) {	
        model.addAttribute("students", repository.findAll());
        return "studentlist";
    }
  
    @RequestMapping(value = "/add")
    public String addStudent(Model model){
    	model.addAttribute("student", new Student());
        return "addstudent";
    }       

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long studentId, Model model) {
    	repository.deleteById(studentId);
        return "redirect:../studentlist";
    }     
}