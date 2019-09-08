package todoapp.web.todo;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import todoapp.core.todos.application.TodoEditor;
import todoapp.core.todos.application.TodoFinder;
import todoapp.core.todos.domain.Todo;

@RestController
@RolesAllowed({ "ROLE_USER" })
@RequestMapping("/api/todos")
public class TodoRestController {

	private final Logger log = LoggerFactory.getLogger(TodoRestController.class);
	private TodoFinder todoFinder;
	private TodoEditor todoEditor;

	public TodoRestController(TodoFinder todoFinder, TodoEditor todoEditor) {
		this.todoFinder = todoFinder;
		this.todoEditor = todoEditor;
	}

	@GetMapping
	public List<Todo> todos() {
		return todoFinder.getAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid TodoWriteCommand command) {
		log.debug("title: {}", command.getTitle());
		todoEditor.create(command.getTitle());
	}

	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody TodoWriteCommand command) {
		log.debug("id: {}, title: {}, completed: {}", id, command.getTitle(), command.isCompleted());
		todoEditor.update(id, command.getTitle(), command.isCompleted());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.debug("id: {}", id);
		todoEditor.delete(id);
	}

	// Command 라는 이름은 spring 2.5부터 내려온 규약
	static class TodoWriteCommand {

		@Size(min = 4, max = 140)
		private String title;
		private boolean completed;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public boolean isCompleted() {
			return completed;
		}

		public void setCompleted(boolean completed) {
			this.completed = completed;
		}

	}
}
