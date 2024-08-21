package com.sysaid.assignment.controller;

import com.sysaid.assignment.dto.AddRequestTaskToUserWishListDto;
import com.sysaid.assignment.dto.AddResponseTaskToUserWishListDto;
import com.sysaid.assignment.dto.CompleteRequestTaskDto;
import com.sysaid.assignment.dto.CompleteResponseTaskDto;
import com.sysaid.assignment.dto.CreateRequestTaskDto;
import com.sysaid.assignment.dto.CreateResponseTaskDto;
import com.sysaid.assignment.dto.DeleteRequestTaskDto;
import com.sysaid.assignment.dto.DeleteResponseTaskDto;
import com.sysaid.assignment.dto.GetRequestTaskDto;
import com.sysaid.assignment.dto.GetRequestUncompletedTaskDto;
import com.sysaid.assignment.dto.GetRequestСompletedTaskDto;
import com.sysaid.assignment.dto.GetResponseTaskDto;
import com.sysaid.assignment.dto.GetResponseTasksByRating;
import com.sysaid.assignment.dto.GetResponseUserWishListTaskDto;
import com.sysaid.assignment.dto.UpdateRequestTaskDto;
import com.sysaid.assignment.dto.UpdateResponseTaskDto;
import com.sysaid.assignment.service.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

	private final ITaskService taskService;

	@GetMapping("/uncompleted-tasks/{user}")
	public ResponseEntity<GetRequestUncompletedTaskDto[]> getUncompletedTasks(@PathVariable ("user") String user,
													  @RequestParam(name = "type", required = false) String type) {
		return taskService.getUncompletedTasks(user, type);
	}

	@GetMapping("/completed-tasks/{user}")
	public ResponseEntity<GetRequestСompletedTaskDto[]> getCompletedTasks(@PathVariable ("user") String user) {
		return taskService.getCompletedTasks(user);
	}

	@GetMapping
	public ResponseEntity<GetResponseTasksByRating[]> getTasksByRating(
			@RequestParam(name = "rating", required = false) Integer rating) { //TODO it is necessary to clarify whether we are tied to a specific user
		return taskService.getTasksByRating(rating);
	}

	@GetMapping("/task-of-the-day")
	public  ResponseEntity<CreateResponseTaskDto> getTaskOfTheDay(){ //TODO clarify why the server returns 503
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
	public ResponseEntity<GetResponseUserWishListTaskDto[]> getUserWishList(@PathVariable ("username") String username) {
		return taskService.getUserWishList(username);
	}

	@PostMapping("/complete")
	public ResponseEntity<CompleteResponseTaskDto> completeTask(@RequestBody CompleteRequestTaskDto completeRequestTaskDto) {
		return taskService.completeTask(completeRequestTaskDto);
	}

	@PostMapping("/add-to-wishlist")
	public ResponseEntity<AddResponseTaskToUserWishListDto[]> addTaskToUserWishList(@RequestBody AddRequestTaskToUserWishListDto addRequestTaskToUserWishListDto) {
		return taskService.addTaskToUserWishList(addRequestTaskToUserWishListDto);
	}
}

