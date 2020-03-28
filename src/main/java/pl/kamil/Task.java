package pl.kamil;

import java.time.Instant;

class Task {
  private String guid;
  private Instant createdAt;
  private Instant dueDate;
  private String description;

  Task(String guid, Instant dueDate, String description) {
    this.guid = guid;
    this.dueDate = dueDate;
    this.description = description;
    this.createdAt = Instant.now();
  }

  public String getGuid() {
    return guid;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getDueDate() {
    return dueDate;
  }

  public String getDescription() {
    return description;
  }
}
