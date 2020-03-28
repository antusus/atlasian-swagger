package pl.kamil;

import java.time.Instant;
import java.util.Set;

public class TaskWithTags extends Task {
  private final Set<String> tags;

  TaskWithTags(String guid, Instant dueDate, String description, Set<String> tags) {
    super(guid, dueDate, description);
    this.tags = tags;
  }

  public Set<String> getTags() {
    return tags;
  }
}
