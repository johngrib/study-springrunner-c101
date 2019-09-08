package todoapp.web.user;

import java.net.URI;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import todoapp.core.user.application.ProfilePictureChanger;
import todoapp.core.user.domain.ProfilePicture;
import todoapp.core.user.domain.ProfilePictureStorage;
import todoapp.core.user.domain.User;
import todoapp.security.UserSession;
import todoapp.security.UserSessionRepository;
import todoapp.web.model.UserProfile;

@RolesAllowed({ "ROLE_USER" })
@RestController
public class UserRestController {

    private final Logger log = LoggerFactory.getLogger(UserRestController.class);
    private ProfilePictureChanger profilePictureChanger;
    private ProfilePictureStorage profilePictureStorage;
    private UserSessionRepository sessionRepository;

    public UserRestController(ProfilePictureChanger profilePictureChanger, ProfilePictureStorage profilePictureStorage,
            UserSessionRepository sessionRepository) {
        this.profilePictureChanger = profilePictureChanger;
        this.profilePictureStorage = profilePictureStorage;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/api/user/profile")
    public UserProfile userProfile(UserSession session) {
        return new UserProfile(session.getUser());
    }

    @PostMapping("/api/user/profile-picture")
    public UserProfile changePorfilePicture(MultipartFile profilePicture, UserSession session) {
        log.debug("profilePicture: {}", profilePicture);

        // 프로필 이미지 변경
        URI profilePictureUri = profilePictureStorage.save(profilePicture.getResource());
        User changedUser = profilePictureChanger.change(session.getName(), new ProfilePicture(profilePictureUri));

        // 프로필 이미지 변경 후 세션 업데이트
        sessionRepository.set(new UserSession(changedUser));

        return new UserProfile(session.getUser());
    }
}
