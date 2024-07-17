package com.project.demo.LocationUnitTest;

import com.project.demo.Config.TestConfig;
import com.project.demo.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@Import(TestConfig.class)
public class LocationRepositoryTest {
    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testFindById() {
        // Setup data in the in-memory database
        // Test the findById method
    }
}
