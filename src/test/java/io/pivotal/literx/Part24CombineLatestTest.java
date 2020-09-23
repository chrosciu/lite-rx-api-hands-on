package io.pivotal.literx;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Part24CombineLatestTest {

    private Part24CombineLatest workshop = new Part24CombineLatest();

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
