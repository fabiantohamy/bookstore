package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.AppUser;
import fi.haagahelia.course.domain.AppUserRepository;
import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;

@SpringBootApplication
public class StudentListApplication {
    private static final Logger log = LoggerFactory.getLogger(StudentListApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StudentListApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CategoryRepository categoryRepository, BookRepository bookRepository, AppUserRepository userRepository) {
        return (args) -> {
            log.info("save a couple of books");
            categoryRepository.save(new Category("Fiction"));
            categoryRepository.save(new Category("Non-Fiction"));
            categoryRepository.save(new Category("Science"));
            categoryRepository.save(new Category("Fantasy"));

            Category fictionCategory = categoryRepository.findByName("Fiction").get(0);
            Category fantasyCategory = categoryRepository.findByName("Fantasy").get(0);

            bookRepository.save(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "978-0747532699", 1997, fictionCategory));
            bookRepository.save(new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0261102385", 1954, fantasyCategory));
            
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

            log.info("fetch all books");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
        };
    }
}