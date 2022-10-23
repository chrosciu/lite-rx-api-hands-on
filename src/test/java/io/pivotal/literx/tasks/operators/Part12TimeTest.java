package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part12TimeTest {

    Part12Time workshop = new Part12Time();

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

//========================================================================================

    private List<Tuple2<String, Integer>> flux1Items = Arrays.asList(
            Tuples.of("A", 2000),
            Tuples.of("B", 3000),
            Tuples.of("C", 5000)
    );

    private List<Tuple2<String, Integer>> flux2Items = Arrays.asList(
            Tuples.of("1", 1000),
            Tuples.of("2", 1500),
            Tuples.of("3", 4000),
            Tuples.of("4", 4500)
    );

    @Test
    public void createFluxWithDelaysTest() {
        StepVerifier.withVirtualTime(() -> workshop.createFluxFromItemsWithDelaysInMs(flux1Items))
                .expectSubscription()
                .expectNoEvent(Duration.ofMillis(2000))
                .expectNext("A")
                .expectNoEvent(Duration.ofMillis(1000))
                .expectNext("B")
                .expectNoEvent(Duration.ofMillis(2000))
                .expectNext("C")
                .verifyComplete();
    }

//========================================================================================

    private Flux<String> createAndCombine() {
        Flux<String> flux1 = workshop.createFluxFromItemsWithDelaysInMs(flux1Items);
        Flux<String> flux2 = workshop.createFluxFromItemsWithDelaysInMs(flux2Items);
        return workshop.combineLatestWithConcat(flux1, flux2);
    }

    @Test
    public void combineLatestTestWithVirtualTime() {
        StepVerifier.withVirtualTime(this::createAndCombine)
                .thenAwait(Duration.ofMillis(5000))
                .expectNext("A2", "B2", "B3", "B4", "C4")
                .verifyComplete();
    }

}
