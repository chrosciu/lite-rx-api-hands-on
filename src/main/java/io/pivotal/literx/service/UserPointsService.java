package io.pivotal.literx.service;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.event.EventSource;
import reactor.core.publisher.Flux;

public interface UserPointsService {
    Flux<String> getStarPoints(User user);
    EventSource<Integer> getNumericPointsEventSource(User user);
}
