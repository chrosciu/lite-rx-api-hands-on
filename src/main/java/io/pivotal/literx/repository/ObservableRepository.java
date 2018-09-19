package io.pivotal.literx.repository;

import io.reactivex.Observable;

public interface ObservableRepository<T> {
    Observable<T> findAll();
}
