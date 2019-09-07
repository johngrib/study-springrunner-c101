package todoapp.web.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Controller
public class TodoController {
	
		@RequestMapping("/todos")
		public ModelAndView todos() {
			
			// ThymeleafViewResolver, Spring boot가 Spring MVC에게 등록 처리 
			// 머릿글 => classpath:templates/, 꼬릿글=> .html
			ModelAndView mav = new ModelAndView("todos");
			
			return mav;
		}
}
