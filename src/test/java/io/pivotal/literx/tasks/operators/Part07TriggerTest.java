package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.publisher.TestPublisher;

public class Part07TriggerTest {

    private Part07Trigger workshop = new Part07Trigger();

    //========================================================================================

    @Test
    public void triggerNewStreamWhenGivenStreamCompletesTest() {
        TestPublisher<User> testPublisher = TestPublisher.create();
        Flux<String> newStream = Flux.just("A", "B");
        StepVerifier.create(workshop.triggerNewStreamWhenStreamCompletes(testPublisher.flux(), newStream))
                .expectSubscription()
                .then(() -> testPublisher.next(User.SKYLER))
                .expectNextCount(0)
                .then(() -> testPublisher.next(User.JESSE))
                .expectNextCount(0)
                .then(testPublisher::complete)
                .expectNext("A", "B")
                .verifyComplete();
    }

    //========================================================================================

    @Test
    public void completeWhenGivenStreamCompletesTest() {
        TestPublisher<User> testPublisher = TestPublisher.create();
        StepVerifier.create(workshop.triggerCompleteWhenStreamCompletes(testPublisher.flux()))
                .expectSubscription()
                .then(() -> testPublisher.next(User.SKYLER))
                .expectNextCount(0)
                .then(() -> testPublisher.next(User.JESSE))
                .expectNextCount(0)
                .then(testPublisher::complete)
                .verifyComplete();
    }

    //========================================================================================

    @Test
    public void completeWhenAllStreamCompleteTest() {
        TestPublisher<User> testPublisher1 = TestPublisher.create();
        TestPublisher<String> testPublisher2 = TestPublisher.create();
        StepVerifier.create(workshop.triggerCompleteWhenAllStreamsComplete(
                testPublisher1.flux(), testPublisher2.flux()))
                .expectSubscription()
                .then(() -> testPublisher1.next(User.SKYLER))
                .expectNextCount(0)
                .then(() -> testPublisher2.next("A"))
                .expectNextCount(0)
                .then(testPublisher2::complete)
                .expectNextCount(0)
                .then(testPublisher1::complete)
                .verifyComplete();
    }

    //========================================================================================

    @Test
    public void returnFluxWithSkylerAndJesseWhenStreamIsEmptyTest() {
        StepVerifier.create(
                workshop.returnFluxWithSkylerAndJesseWhenStreamIsEmpty(Flux.just(User.SAUL, User.WALTER)),
                        StepVerifierOptions.create().scenarioName("Not empty stream"))
                .expectNext(User.SAUL, User.WALTER)
                .verifyComplete();

        StepVerifier.create(workshop.returnFluxWithSkylerAndJesseWhenStreamIsEmpty(Flux.empty()),
                        StepVerifierOptions.create().scenarioName("Empty stream"))
                .expectNext(User.SKYLER, User.JESSE)
                .verifyComplete();
    }

}
