package todoapp.web.user;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import todoapp.security.UserSession;
import todoapp.web.model.UserProfile;

@RolesAllowed({ "ROLE_USER" })
@RestController
public class UserRestController {

    private final Logger log = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping("/api/user/profile")
    public UserProfile userProfile(UserSession session) {
        return new UserProfile(session.getUser());
    }

    @PostMapping("/api/user/profile-picture")
    public UserProfile changePorfilePicture(MultipartFile profilePicture, UserSession session) {
        log.debug("profilePicture: {}", profilePicture);
        return new UserProfile(session.getUser());
    }
}
