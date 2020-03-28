package pl.kamil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TasksRouter {
  @Bean
  public RouterFunction<ServerResponse> route(TasksHandler handler) {

    return RouterFunctions
        .route(RequestPredicates
                .GET("/tasks")
                .and(RequestPredicates.accept(new MediaType("application", "v1+json"))
                ),
            handler::getAll)
        .andRoute(
            RequestPredicates
                .GET("/tasks")
                .and(RequestPredicates.accept(new MediaType("application", "v2+json"))
                ),
            handler::getAllWithTags
        )
        ;
  }
}
