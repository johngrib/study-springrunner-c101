package todoapp.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import todoapp.core.user.application.UserJoinder;
import todoapp.core.user.application.UserPasswordVerifier;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    private final Logger log = LoggerFactory.getLogger(LoginController.class);
    private UserPasswordVerifier verifier;
    private UserJoinder joinder;

    public LoginController(UserPasswordVerifier verifier, UserJoinder joinder) {
        this.verifier = verifier;
        this.joinder = joinder;
    }

    @GetMapping
    public void loginForm() {
    }

    /**
     * 사용자 저장소에 사용자가 있을 경우: 비밀번호 일치 확인
     *      비밀번호가 일치하면: /todos 리다이렉트
     *      비밀번호가 다르면: login 페이지로 돌려보내고, 오류 메시지 노출
     * 사용자가 없는 경우: 회원가입 페이지로 돌려보냄
     */
    @PostMapping
    public String loginProcess(LoginCommand command) {
        log.debug("username: {}, password: {}", command.getUsername(), command.getPassword());

        return "login";
    }
    
    static class LoginCommand {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
