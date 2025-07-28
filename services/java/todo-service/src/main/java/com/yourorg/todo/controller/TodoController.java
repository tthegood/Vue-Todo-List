package com.yourorg.todo.controller;

import com.yourorg.todo.model.Todo;
import com.yourorg.todo.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin  // allow from your front‑end origin
public class TodoController {
    private final TodoRepository repo;
    public TodoController(TodoRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Todo> all() {
        return repo.findAll();
    }

    @PostMapping
    public Todo create(@RequestBody Todo t) {
        return repo.save(t.getText());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(
        @PathVariable Long id,
        @RequestBody Todo t
    ) {
        return repo.findById(id)
                   .map(old -> ResponseEntity.ok(repo.update(id, t)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.delete(id);
        return ResponseEntity.noContent().build();
    }
}
