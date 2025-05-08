package org.kernel360.tang.test.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseIntegrationTest {
    @Autowired
    DataSource dataSource;

    @BeforeAll
    void setUp() {
        // Initialize the database or any other setup needed for integration tests
        // This method will be called before all tests in the subclass
        try (var conn = dataSource.getConnection()) {
            // Perform any necessary setup, like creating tables or inserting test data
            // For example:
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("database/init.sql"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up the database for integration tests", e);
        }
    }
}
