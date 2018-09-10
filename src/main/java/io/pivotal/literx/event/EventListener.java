package io.pivotal.literx.event;

public interface EventListener<T> {
    void next(T t);
    void error(Exception e);
    void end();
}
