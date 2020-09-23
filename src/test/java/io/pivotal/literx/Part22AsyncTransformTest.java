package io.pivotal.literx;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Part22AsyncTransformTest {

    private final static int NUMBER_OF_GENERATED_ITEMS = 3;
    private final static long INTERVAL_OF_GENERATED_ITEMS_IN_MS = 130;

    Part22AsyncTransform workshop = new Part22AsyncTransform();

    private Flux<String> sourceSequence() {
        return Flux.interval(Duration.ZERO, Duration.ofMillis(INTERVAL_OF_GENERATED_ITEMS_IN_MS))
                .map(Object::toString)
                .take(NUMBER_OF_GENERATED_ITEMS);
    }

    @Test
    public void transformTest() {
        Flux<String> result = workshop.transform(sourceSequence());
        StepVerifier.create(result)
                .expectNext("0", "00", "1", "000", "11", "2", "111", "22", "222")
                .verifyComplete();
    }

    @Test
    public void transformWithOrderTest() {
        Flux<String> result = workshop.transformWithOrder(sourceSequence());
        StepVerifier.create(result)
               .expectNext("0", "00", "000", "1", "11", "111", "2", "22", "222")
               .verifyComplete();
    }

    @Test
    public void transformUntilNextTest() {
        Flux<String> result = workshop.transformUntilNext(sourceSequence());
        StepVerifier.create(result)
                .expectNext("0", "00", "1", "11", "2", "22", "222")
                .verifyComplete();
    }

}
