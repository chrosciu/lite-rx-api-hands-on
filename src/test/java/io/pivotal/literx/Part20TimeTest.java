package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part20TimeTest {

    Part20Time workshop = new Part20Time();

    final static User MARIE = new User("mschrader", "Marie", "Schrader");
    final static User MIKE = new User("mehrmantraut", "Mike", "Ehrmantraut");


//========================================================================================

    @Test
    public void fastestMono() {
        ReactiveRepository<User> repository = new ReactiveUserRepository(MARIE);
        ReactiveRepository<User> repositoryWithDelay = new ReactiveUserRepository(250, MIKE);
        Mono<User> mono = workshop.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst());
        StepVerifier.create(mono)
                .expectNext(MARIE)
                .verifyComplete();

        repository = new ReactiveUserRepository(250, MARIE);
        repositoryWithDelay = new ReactiveUserRepository(MIKE);
        mono = workshop.useFastestMono(repository.findFirst(), repositoryWithDelay.findFirst());
        StepVerifier.create(mono)
                .expectNext(MIKE)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void fastestFlux() {
        ReactiveRepository<User> repository = new ReactiveUserRepository(MARIE, MIKE);
        ReactiveRepository<User> repositoryWithDelay = new ReactiveUserRepository(250);
        Flux<User> flux = workshop.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll());
        StepVerifier.create(flux)
                .expectNext(MARIE, MIKE)
                .verifyComplete();

        repository = new ReactiveUserRepository(250, MARIE, MIKE);
        repositoryWithDelay = new ReactiveUserRepository();
        flux = workshop.useFastestFlux(repository.findAll(), repositoryWithDelay.findAll());
        StepVerifier.create(flux)
                .expectNext(User.SKYLER, User.JESSE, User.WALTER, User.SAUL)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void fluxWithTwoSecondsTimeoutTest() {
        StepVerifier.withVirtualTime(() -> {
            Flux<User> userFlux = Flux.just(SKYLER, JESSE, WALTER).delayUntil(this::getUserDelayFlux);
            Flux<User> resultFlux = workshop.fluxWithTwoSecondsTimeout(userFlux);
            return resultFlux;
        })
                .thenAwait(Duration.ofSeconds(4))
                .expectNext(SKYLER, JESSE)
                .verifyError(TimeoutException.class);
    }

    private Flux<Integer> getUserDelayFlux(User user) {
        Duration delay = Duration.ZERO;
        if (JESSE == user) {
            delay = Duration.ofSeconds(1);
        } else if (WALTER == user) {
            delay = Duration.ofSeconds(3);
        }
        return Flux.just(0).delayElements(delay);
    }
}




