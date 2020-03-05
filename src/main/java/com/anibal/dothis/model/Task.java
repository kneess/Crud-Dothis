package com.anibal.dothis.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private Long id;
	
	@NotEmpty(message="Please enter in a task")
	@Column(name="task_name")
	private String taskName;
	
	@NotEmpty(message="Please enter in a task description")
	@Column(name="task_notes")
	private String taskNotes;
	
	@NotNull(message="Enter in a date. (include forward slashes) Format: dd/MM/yyyy")
	@Column(name="due_date")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dueDate;
	
	@ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="tasklist_id")
	private Tasklist tasklist;
	
	public Task() {
		
	}

	public Task(String taskName, String taskNotes, Date dueDate) {
		this.taskName = taskName;
		this.taskNotes = taskNotes;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskNotes() {
		return taskNotes;
	}

	public void setTaskNotes(String taskNotes) {
		this.taskNotes = taskNotes;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Tasklist getTasklist() {
		return tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskNotes=" + taskNotes + ", dueDate="
				+ dueDate + "]";
	}
}
