package com.sysaid.assignment.controller;

import com.sysaid.assignment.domain.Task;
import com.sysaid.assignment.dto.*;
import com.sysaid.assignment.service.ITaskService;
import com.sysaid.assignment.service.impl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

	private final ITaskService taskService;

	@GetMapping("/uncompleted-tasks/{user}")
	public ResponseEntity<GetRequestUncompletedTaskDto[]> getUncompletedTasks(@PathVariable ("user") String user,
													  @RequestParam(name = "type",required = false) String type){
		return taskService.getUncompletedTasks(user, type);
	}

	@GetMapping("/completed-tasks/{user}")
	public ResponseEntity<GetRequestСompletedTaskDto[]> getCompletedTasks(@PathVariable ("user") String user){
		return taskService.getCompletedTasks(user);
	}

	@GetMapping("/task-of-the-day")
	public  ResponseEntity<Task> getTaskOfTheDay(){
		return taskService.getRandomTask();
	}

	@PostMapping("/create")
	public ResponseEntity<CreateResponseTaskDto> createTask(@RequestBody CreateRequestTaskDto createRequestTaskDto) {
		return taskService.createTask(createRequestTaskDto);
	}

	@PutMapping("/update")
	public ResponseEntity<UpdateResponseTaskDto> updateTask(@RequestBody UpdateRequestTaskDto updateTaskDto) {
		return taskService.updateTask(updateTaskDto);
	}

	@GetMapping("/get")
	public ResponseEntity<GetResponseTaskDto> getTask(@RequestBody GetRequestTaskDto getRequestTaskDto) {
		return taskService.getTask(getRequestTaskDto);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<DeleteResponseTaskDto> deleteTask(@RequestBody DeleteRequestTaskDto deleteRequestTaskDto) {
		return taskService.deleteTask(deleteRequestTaskDto);
	}

	@GetMapping("/user-wishlist/{username}")
	public ResponseEntity<GetRequestUserWishListTaskDto[]> getUserWishList(@PathVariable ("username") String username) {
		return taskService.getUserWishList(username);
	}

	@PostMapping("/complete")
	public ResponseEntity<Task> completeTask(@RequestBody CompleteRequestTaskDto completeRequestTaskDto) {
		return taskService.completeTask(completeRequestTaskDto);
	}

	@PostMapping("/add-to-wishlist")
	public ResponseEntity<Task[]> addTaskToUserWishList(@RequestBody AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto) {
		return taskService.addTaskToUserWishList(addRequestTaskToUserWishListDto);
	}
}

