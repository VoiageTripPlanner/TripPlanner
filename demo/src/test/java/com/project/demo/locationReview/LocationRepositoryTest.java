package com.project.demo.locationReview;

import com.project.demo.Config.TestConfig;
import com.project.demo.entity.Location;
import com.project.demo.repository.LocationRepository;

import org.assertj.core.api.Assertions;
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
    public void LocationRepository_SaveAll_ReturnSavedLocations() {
        //Arrange
        Location location = new Location( 1, "1234 Main St", 123.456, 123.456, "1234");

        //Act
        Location savedLocation = locationRepository.save(location);

        //Assert
        Assertions.assertThat(savedLocation).isNotNull();
        Assertions.assertThat(savedLocation.getLocationId()).isGreaterThan(0);
    }

}
