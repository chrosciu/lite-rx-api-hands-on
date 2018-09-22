package io.pivotal.literx.repository;

import io.reactivex.Observable;

public interface IterableRepository<T> {
    Iterable<T> findAll();
}
