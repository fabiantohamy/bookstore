package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public CommandLineRunner demo(CategoryRepository categoryRepository) {
        return (args) -> {
            categoryRepository.save(new Category("Fiction"));
            categoryRepository.save(new Category("Non-Fiction"));
            categoryRepository.save(new Category("Science"));
            categoryRepository.save(new Category("Fantasy"));
        };
    }
}