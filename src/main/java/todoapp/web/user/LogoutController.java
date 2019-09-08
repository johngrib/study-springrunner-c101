package todoapp.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import todoapp.security.UserSessionRepository;

@Controller
public class LogoutController {

    private UserSessionRepository sessionRepository;

    public LogoutController(UserSessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping("/logout")
    public View logout() {
        sessionRepository.clear();
        return new RedirectView("/todos");
    }
}
