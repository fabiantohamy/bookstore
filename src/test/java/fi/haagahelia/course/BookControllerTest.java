package fi.haagahelia.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/hal+json"));
    }

    @Test
    public void testCreateBook() throws Exception {
        String newBook = "{\"title\":\"A Head Full of Ghosts\",\"author\":\"Paul Tremblay\",\"isbn\":\"978-0132350884\",\"year\":2015,\"category\":{\"name\":\"Classics\"}}";
        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBook))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/api/books")
            .param("title", "A Head Full of Ghosts"))
            .andExpect(status().isNoContent());
    }
}
