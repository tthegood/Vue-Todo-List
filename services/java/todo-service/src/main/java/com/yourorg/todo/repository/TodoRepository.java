package com.yourorg.todo.repository;

import com.yourorg.todo.model.Todo;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    private final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public List<Todo> findAll() {
        return new ArrayList<>(store.values());
    }
    public Todo save(String text) {
        Long id = seq.incrementAndGet();
        Todo t = new Todo(id, text, false);
        store.put(id, t);
        return t;
    }
    public Optional<Todo> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
    public Todo update(Long id, Todo t) {
        t.setId(id);
        store.put(id, t);
        return t;
    }
    public void delete(Long id) {
        store.remove(id);
    }
}
