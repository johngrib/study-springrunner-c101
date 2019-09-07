package todoapp.web.todo;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import todoapp.core.todos.application.TodoFinder;
import todoapp.web.convert.TodoToSpreadsheetConverter;
import todoapp.web.model.SiteProperties;

@Controller
public class TodoController {

	private Environment env;
	private SiteProperties properties;
	private TodoFinder todoFinder;

	public TodoController(Environment env, SiteProperties properties, TodoFinder todoFinder) {
		this.env = env;
		this.properties = properties;
		this.todoFinder = todoFinder;
	}

	@RequestMapping("/todos")
	public ModelAndView todos() {

		// ThymeleafViewResolver, Spring boot가 Spring MVC에게 등록 처리
		// 머릿글 => classpath:templates/, 꼬릿글=> .html

		ModelAndView mav = new ModelAndView("todos");
		mav.addObject("site", properties);
		mav.addObject(new TodoToSpreadsheetConverter().convert(todoFinder.getAll()));

		return mav;
	}
}
