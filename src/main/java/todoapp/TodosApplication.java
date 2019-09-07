package todoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import todoapp.core.todos.domain.Todo;
import todoapp.core.todos.domain.TodoRepository;

@SpringBootApplication
public class TodosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosApplication.class, args);
	}

	@Component
	static class TodoDataInit implements InitializingBean, CommandLineRunner, ApplicationRunner {

		private final Logger log = LoggerFactory.getLogger(TodoDataInit.class);

		private TodoRepository todoRepository;

		public TodoDataInit(TodoRepository todoRepository) {
			this.todoRepository = todoRepository;
		}

		@Override
		public void afterPropertiesSet() throws Exception {
			todoRepository.save(Todo.create("create one"));
		}

		@Override
		public void run(String... args) throws Exception {
			todoRepository.save(Todo.create("Task two"));
		}

		@Override
		public void run(ApplicationArguments args) throws Exception {
			log.debug("application run.");
		}

	}
}
