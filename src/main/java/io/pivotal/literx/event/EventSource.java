package io.pivotal.literx.event;

public interface EventSource<T> {
    void registerListener(EventListener<T> listener);
}
