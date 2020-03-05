package com.anibal.dothis.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anibal.dothis.model.Task;
import com.anibal.dothis.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskRestController {

	private TaskService taskService;
	
	public TaskRestController(TaskService theTaskService) {
		taskService = theTaskService;
	}
	
	@GetMapping("/tasks")
	public List<Task> allTasks(Long id){
		return taskService.getAllTasks(id);
	}
	
	@PostMapping("/tasks")
	public Task postTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}
	
	@GetMapping("/tasks/{task_id}")
	public Task getTask(@PathVariable("task_id") Long id) {
		return taskService.getTaskById(id);
	}
	
	@PutMapping("/tasks")
	public Task updateTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}
	
	@DeleteMapping("/tasks/{task_id}")
	public String deleting(@PathVariable("task_id") Long id) {
		taskService.deleteTaskFromList(id);
		return "Task Deleted";
	}
}











