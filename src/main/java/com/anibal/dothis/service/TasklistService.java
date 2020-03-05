package com.anibal.dothis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anibal.dothis.model.Tasklist;
import com.anibal.dothis.repository.TasklistRepository;

@Service
public class TasklistService {
	private TasklistRepository repository;
	
	@Autowired
	public TasklistService(TasklistRepository theRepository) {
		repository = theRepository;
	}
	
	public Tasklist getTasklistById(Long id) {
		return repository.findTasklistById(id);
	}
	
	public Tasklist getTasklistByName(String taskName) {
		return repository.findTasklistByTasklistName(taskName);
	}
	
	public List<Tasklist> getAllTasklists(){
		return repository.findAllByOrderByTasklistNameAsc();
	}
	
	public Tasklist saveTasklist(Tasklist task) {
		return repository.save(task);
	}
	
	public void deleteTasklist(Long id) {
		repository.deleteById(id);
	}
}
