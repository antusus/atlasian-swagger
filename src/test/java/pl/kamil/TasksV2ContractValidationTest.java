package pl.kamil;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TasksV2ContractValidationTest {

  @LocalServerPort
  private int port;
  private OpenApiValidationFilter validationFilter;

  @BeforeEach
  void setUp() throws URISyntaxException {
    URI uri = this.getClass().getResource("/static/apidocs/tasks-v2.yml").toURI();
    OpenApiInteractionValidator validator = OpenApiInteractionValidator.createFor(Paths.get(uri).toString()).build();
    validationFilter = new OpenApiValidationFilter(validator);
  }

  @Test
  void validate_getTasksV2() {
    given()
        .port(port)
        .filter(validationFilter)
        .header("Accept", "application/v2+json")
        .when()
        .get("/tasks")
        .then()
        .assertThat()
        .statusCode(200);
  }
}
