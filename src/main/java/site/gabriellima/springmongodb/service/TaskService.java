package site.gabriellima.springmongodb.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.gabriellima.springmongodb.domain.Task;
import site.gabriellima.springmongodb.exception.ObjectNotFoundException;
import site.gabriellima.springmongodb.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(String id) {
        return taskRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
    }

    public void deleteById(String id) {
        findById(id);
        taskRepository.deleteById(id);
    }

    public Task update(Task updatedTask) {
        Task oldTask = findById(updatedTask.getId());
        BeanUtils.copyProperties(updatedTask, oldTask, "id");
        return taskRepository.save(oldTask);
    }

    public Task insert(Task newTask) {
        return taskRepository.insert(newTask);
    }
}
