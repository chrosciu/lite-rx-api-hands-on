package io.pivotal.literx.repository;

import io.pivotal.literx.domain.User;
import io.reactivex.Observable;

public class ObservableUserRepository implements ObservableRepository<User> {
    private final User DUMMY = new User(null, null, null);
    @Override
    public Observable<User> findAll() {
        return Observable.fromArray(User.SKYLER, User.SAUL, DUMMY, User.WALTER, User.JESSE);
    }
}
