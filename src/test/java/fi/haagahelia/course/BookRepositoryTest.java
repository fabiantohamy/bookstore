package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        List<Category> categories = categoryRepository.findByName("Classics");
        if (categories.isEmpty()) {
            Category category = new Category("Classics");
            categoryRepository.save(category);
        }
    }

    @Test
    public void findByTitleShouldReturnAuthor() {
        List<Book> books = bookRepository.findByTitle("The Lord of the Rings");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("J.R.R. Tolkien");
    }

    @Test
    public void createNewBook() {
        Category category = categoryRepository.findByName("Classics").get(0);
        Book book = new Book("A Head Full of Ghosts", "Paul Tremblay", "978-0132350884", 2015, category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByTitle("The Lord of the Rings");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByTitle("The Lord of the Rings");
        assertThat(newBooks).hasSize(0);
    }
}