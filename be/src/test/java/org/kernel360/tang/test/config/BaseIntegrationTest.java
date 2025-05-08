package org.kernel360.tang.test.config;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/database/init.sql"})
@ActiveProfiles("test")
public abstract class BaseIntegrationTest {
}
