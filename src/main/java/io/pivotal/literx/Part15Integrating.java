package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.event.EventListener;
import io.pivotal.literx.event.EventSource;
import io.pivotal.literx.repository.ObservableRepository;
import io.pivotal.literx.service.UserPointsService;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class Part15Integrating {

//========================================================================================
    // TODO Sum all points for all users (beware of traps !). Discard users whose sum of points is less than or equal to 5
    public Mono<Integer> sumUsersPoints(ObservableRepository<User> userObservableRepository, UserPointsService userPointsService) {
        Observable<User> users = userObservableRepository.findAll();
        Flux<User> userFlux = Flux.from(users.toFlowable(BackpressureStrategy.MISSING));
        Flux<Integer> points = userFlux.flatMap((Function<User, Publisher<Integer>>) user ->
                userPointsService.getStarPoints(user).onErrorReturn("").map(String::length)
                        .concatWith(eventSource2Flux(userPointsService.getNumericPointsEventSource(user)).onErrorReturn(0))
                        .filter(p -> p > 5));
        Mono<Integer> sum = points.reduce((i1, i2) -> i1 + i2);
        return sum;
    }

    private Flux<Integer> eventSource2Flux(EventSource<Integer> eventSource) {
        return Flux.create(sink -> {
            eventSource.registerListener(new EventListener<Integer>() {
                @Override
                public void next(Integer integer) {
                    sink.next(integer);
                }
                @Override
                public void error(Exception e) {
                    sink.error(e);
                }
                @Override
                public void end() {
                    sink.complete();
                }
            });
        });
    }
}
