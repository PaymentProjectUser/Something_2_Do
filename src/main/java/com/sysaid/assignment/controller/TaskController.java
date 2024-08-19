package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.service.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TaskController {

	private final TaskServiceImpl taskService;

    /**
	 * will return uncompleted tasks for given user
	 * @param user the user which the tasks relevant for
	 * @param type type of the task
	 * @return list uncompleted tasks for the user
	 */
	@GetMapping("/uncompleted-tasks/{user}")
	public ResponseEntity<Task[]> getUncomplitedTasks(@PathVariable ("user") String user, @RequestParam(name = "type",required = false) String type){
		return null;
	}

	/**
	 * example for simple API use
	 * @return random task of the day
	 */
	@GetMapping("/task-of-the-day")
	public  ResponseEntity<Task> getTaskOfTheDay(){
		//example of service use
		return taskService.getRandomTask();
	}


}

