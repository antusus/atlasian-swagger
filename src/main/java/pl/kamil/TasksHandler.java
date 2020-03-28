package pl.kamil;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class TasksHandler {
  Mono<ServerResponse> getAll(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(List.of(taskResponse())).map(ItemsResponse::new), ItemsResponse.class);
  }

  Mono<ServerResponse> getAllWithTags(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(List.of(taskWithTagsResponse())).map(ItemsResponse::new), ItemsResponse.class);
  }

  private Task taskResponse() {
    return new Task(UUID.randomUUID().toString(),
        Instant.now().plus(2, ChronoUnit.DAYS),
        "Check on bug report");
  }

  private Task taskWithTagsResponse() {
    return new TaskWithTags(UUID.randomUUID().toString(),
        Instant.now().plus(2, ChronoUnit.DAYS),
        "Check on bug report",
        Set.of("work", "bugs"));
  }
}
