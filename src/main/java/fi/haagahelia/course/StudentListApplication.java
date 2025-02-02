package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;

@SpringBootApplication
public class StudentListApplication {
    private static final Logger log = LoggerFactory.getLogger(StudentListApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentListApplication.class, args);
    }

    @Bean
    public CommandLineRunner bookDemo(BookRepository repository) {
        return (args) -> {
            log.info("save a couple of books");
            repository.save(new Book("Book Title 1", "Author 1", 2021));
            repository.save(new Book("Book Title 2", "Author 2", 2022));

            log.info("fetch all books");
            for (Book book : repository.findAll()) {
                log.info(book.toString());
            }
        };
    }
}