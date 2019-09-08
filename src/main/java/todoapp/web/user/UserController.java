package todoapp.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/login")
    public ModelAndView loginForm() {

        return new ModelAndView("login");
    }

}
