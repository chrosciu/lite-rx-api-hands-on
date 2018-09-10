package io.pivotal.literx.event;

import io.pivotal.literx.domain.User;

import java.util.Arrays;
import java.util.List;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class UserEventSource implements EventSource<User> {
    private static final List<User> USERS = Arrays.asList(WALTER, SAUL, SKYLER, JESSE);

    @Override
    public void registerListener(EventListener<User> listener) {
        USERS.forEach(listener::next);
        listener.end();
    }
}
