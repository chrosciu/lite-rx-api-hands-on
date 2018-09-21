package io.pivotal.literx.service;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.event.EventSource;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserPointsServiceImpl implements UserPointsService {
    @Override
    public Flux<String> getStarPoints(User user) {
        if (!isUserValid(user)) {
            return Flux.error(new IllegalArgumentException("Strange user!"));
        }
        return Flux.fromIterable(getStarPointsList(user));
    }

    @Override
    public EventSource<Integer> getNumericPointsEventSource(User user) {
        if (!isUserValid(user)) {
            return listener -> {
                listener.error(new IllegalArgumentException("Strange user!"));
            };
        }
        return listener -> {
            getNumericPoints(user).forEach(listener::next);
            listener.end();
        };
    }

    private List<String> getStarPointsList(User user) {
        return Arrays.asList(
                nStars(user.getFirstname().length()),
                nStars(user.getLastname().length()),
                nStars(user.getUsername().length()));
    }

    private List<Integer> getNumericPoints(User user) {
        return Arrays.asList(user.getFirstname().length(), user.getLastname().length(), user.getUsername().length());
    }

    private String nStars(int n) {
        return String.join("", Collections.nCopies(n, "*"));
    }

    private boolean isUserValid(User user) {
        return user != null && user.getFirstname() != null && user.getLastname() != null && user.getUsername() != null;
    }
}
