package site.gabriellima.springmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import site.gabriellima.springmongodb.domain.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
