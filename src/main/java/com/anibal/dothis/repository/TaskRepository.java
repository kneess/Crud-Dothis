package com.anibal.dothis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anibal.dothis.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Task findTaskById(Long id);
	Task findTaskByTaskName(String taskName);
	List<Task> findAllByTasklistIdOrderByDueDateAsc(Long id);
}
