package com.project.demo.LocationUnitTest;

import com.project.demo.Config.TestConfig;
import com.project.demo.logic.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LocationService.class)
public class LocationControllerTest {
    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mockMvc;

        @Test
        void testGetLocation() throws Exception {
            // Mock the service's response
            // Perform a request using MockMvc
            // Assert the response
        }

}
