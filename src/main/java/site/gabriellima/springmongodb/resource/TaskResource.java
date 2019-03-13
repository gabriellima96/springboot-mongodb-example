package site.gabriellima.springmongodb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import site.gabriellima.springmongodb.domain.Task;
import site.gabriellima.springmongodb.service.TaskService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskResource {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") String id) {
        Task task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> insert(@RequestBody Task task) {
        task = taskService.insert(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(task);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody Task task) {
        task.setId(id);
        taskService.update(task);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
