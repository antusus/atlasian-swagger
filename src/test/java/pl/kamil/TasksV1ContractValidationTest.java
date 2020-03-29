package pl.kamil;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TasksV1ContractValidationTest {
  @LocalServerPort
  protected int port;
  protected OpenApiValidationFilter validationFilter;

  @BeforeEach
  void setUp() throws URISyntaxException {
    URI uri = this.getClass().getResource("/static/apidocs/tasks-v1.yml").toURI();
    OpenApiInteractionValidator validator = OpenApiInteractionValidator.createFor(Paths.get(uri).toString()).build();
    validationFilter = new OpenApiValidationFilter(validator);
  }

  @Test
  void validate_getAllTasks() {
    given()
        .port(port)
        .filter(validationFilter)
        .header("Accept", "application/v1+json")
        .when()
        .get("/tasks")
        .then()
        .assertThat()
        .statusCode(OK.value());
  }
}
