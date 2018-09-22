package io.pivotal.literx.repository;

import io.pivotal.literx.domain.User;
import io.reactivex.Observable;

import java.util.Arrays;

public class IterableUserRepository implements IterableRepository<User> {
    private final User DUMMY = new User(null, null, null);
    @Override
    public Iterable<User> findAll() {
        return Arrays.asList(User.SKYLER, User.SAUL, DUMMY, User.WALTER, User.JESSE);
    }
}
