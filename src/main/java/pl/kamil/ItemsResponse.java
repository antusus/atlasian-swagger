package pl.kamil;

import java.util.Collection;

public class ItemsResponse<T> {
  private final Collection<T> items;

  ItemsResponse(Collection<T> items) {
    this.items = items;
  }

  public Collection<T> getItems() {
    return items;
  }
}
