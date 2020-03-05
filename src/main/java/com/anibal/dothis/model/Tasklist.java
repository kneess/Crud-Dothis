package com.anibal.dothis.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tasklist")
public class Tasklist {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tasklist_id")
	private Long id;
	
	@Column(name="tasklist_name")
	private String tasklistName;
	
	@OneToMany(mappedBy="tasklist",
			cascade= {CascadeType.ALL})
	private List<Task> tasks;
	
	public Tasklist() {
		
	}

	public Tasklist(String tasklistName) {
		this.tasklistName = tasklistName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTasklistName() {
		return tasklistName;
	}

	public void setTasklistName(String tasklistName) {
		this.tasklistName = tasklistName;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Tasklist [id=" + id + ", tasklistName=" + tasklistName + "]";
	}
}
