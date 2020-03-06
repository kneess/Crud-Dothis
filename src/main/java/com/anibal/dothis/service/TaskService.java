package com.anibal.dothis.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anibal.dothis.model.Task;
import com.anibal.dothis.repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository repository;
	
	@Autowired
	public TaskService(TaskRepository theRepository) {
		repository = theRepository;
	}
	
	public List<Task> getAllTasks(){
		return repository.findAll();
	}
	
	public Task getTaskById(Long id) {
		return repository.findTaskById(id);
	}
	
	public Task getTaskByName(String taskName) {
		return repository.findTaskByTaskName(taskName);
	}
	
	public List<Task> getAllTasksByTasklistId(Long id){
		return repository.findAllByTasklistIdOrderByDueDateAsc(id);
	}
	
	public Task saveTask(Task task) {
		return repository.save(task);
	}
	
	public void deleteTaskFromList(Long id) {
		repository.deleteById(id);
	}
	
//	public List<Task> allTaskNamesToUpperCaseLambda(List<Task> tasks){
//		tasks.forEach((t) -> {
//			t.setTaskName(t.getTaskName().toUpperCase());
//			}
//		);
//		return tasks;
//	}

	public List<Task> sortTasks(List<Task> tasks){
		Collections.sort(tasks);
		return tasks;
	}
}
