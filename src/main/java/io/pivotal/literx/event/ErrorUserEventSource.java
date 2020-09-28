package io.pivotal.literx.event;

import io.pivotal.literx.domain.User;

public class ErrorUserEventSource implements EventSource<User> {
    @Override
    public void registerListener(EventListener<User> listener) {
        listener.error(new RuntimeException());
    }
}
