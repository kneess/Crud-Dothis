package com.anibal.dothis.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.anibal.dothis.model.Task;
import com.anibal.dothis.model.Tasklist;
import com.anibal.dothis.service.TaskService;
import com.anibal.dothis.service.TasklistService;

@Controller
public class TaskListController {

	@Autowired
	private TasklistService tasklistService;
	
	@Autowired
	private TaskService taskService;
	
	//get mapping to retrive all lists
	@GetMapping(value= {"/lists", "/"})
	public String getLists(Model model){
		
		List<Tasklist> lists = tasklistService.getAllTasklists();
		model.addAttribute("tasklists", lists);
		return "task-lists";
	}
	
	//get mapping to get task list form
	@GetMapping("/lists/addList")
	public String getListForm(Model theModel) {
		
		theModel.addAttribute("tasklist", new Tasklist());
		return "task-list-form";
	}
	
//	get mapping to post a task list
	@PostMapping("/lists/processList")
	public String addList(@Valid Tasklist list, BindingResult bindingResult, Model model){
		
		if(!bindingResult.hasErrors()) {
			tasklistService.saveTasklist(list);
			model.addAttribute("success","tasklist added successfully!");
			model.addAttribute("tasklist", new Tasklist());
		}
		return "task-list-form";
	}
	
	@GetMapping("/lists/delete/{tasklist_id}")
	public RedirectView deleteList(@PathVariable("tasklist_id") Long id) {
		
		tasklistService.deleteTasklist(id);
		return new RedirectView("/lists");
	}
	
	@GetMapping("/lists/{tasklist_id}/tasks")
	public String getlist(@PathVariable("tasklist_id") Long id, Model theModel) {
		Tasklist taskList = tasklistService.getTasklistById(id);
		List<Task> tasks = taskService.sortTasks(taskList.getTasks());
//		List<Task> upperCaseTasks = taskService.allTaskNamesToUpperCaseLambda(tasks);
		theModel.addAttribute("tasklist", taskList);
		theModel.addAttribute("tasks",tasks);
		return "task-list";
	}
	
	@GetMapping("/lists/{tasklist_id}/tasks/addTask")
	public String addTask(@PathVariable("tasklist_id") Long id, Model theModel) {
		Tasklist taskList = tasklistService.getTasklistById(id);
		theModel.addAttribute("tasklist", taskList);
		theModel.addAttribute("task", new Task());
		return "task-form";
	}
	
//	get mapping to post a task to the list
	@PostMapping("/lists/{tasklist_id}/tasks/processTask")
	public String addtasktolist(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult,
			@PathVariable("tasklist_id") Long id, Model model){
		
		Tasklist taskList = tasklistService.getTasklistById(id);
		if(!bindingResult.hasErrors()) {
			task.setTasklist(taskList);
			taskService.saveTask(task);
			model.addAttribute("success","To-do added successfully!");
			model.addAttribute("task", new Task());
		}
		model.addAttribute("tasklist", taskList);
		return "task-form";
	}
	
	@GetMapping("/lists/{tasklist_id}/tasks/{task_id}")
	public String updateTask(@PathVariable("task_id") Long taskId, @PathVariable("tasklist_id") Long tasklistId
			, Model theModel) {
		Task task = taskService.getTaskById(taskId);
		Tasklist taskList = tasklistService.getTasklistById(tasklistId);
		theModel.addAttribute("tasklist", taskList);
		theModel.addAttribute("task", task);
		return "task-form";
	}
	
	@GetMapping("/lists/{tasklist_id}/tasks/delete/{task_id}")
	public RedirectView deleteTask(@PathVariable("task_id") Long taskId, @PathVariable("tasklist_id") Long tasklistId) {
		taskService.deleteTaskFromList(taskId);
		return new RedirectView("/lists/"+ tasklistId + "/tasks");
	}

}
