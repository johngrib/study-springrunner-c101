package todoapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import todoapp.web.support.ConnectedClientCountBroadcaster;

@Controller
public class StreamOnlineUsersCounterController {

    private ConnectedClientCountBroadcaster broadcaster = new ConnectedClientCountBroadcaster();

    @RequestMapping("/stream/online-users-counter")
    public SseEmitter counter() {
        return broadcaster.subscribe();
    }
}
