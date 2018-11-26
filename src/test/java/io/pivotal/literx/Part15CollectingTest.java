package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part15CollectingTest {

    private Part15Collecting workshop = new Part15Collecting();

//========================================================================================

    @Test
    public void concatStringsTest() {
        Flux<String> flux = Flux.just("A", "b", "C");
        Mono<String> mono = workshop.concatStrings(flux);
        StepVerifier.create(mono)
                .expectNext("AbC")
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void includesSkylerTest() {
        Flux<User> fluxWithSkyler = Flux.just(JESSE, SKYLER, WALTER);
        Mono<Boolean> expectedTrue = workshop.includesSkyler(fluxWithSkyler);
        StepVerifier.create(expectedTrue)
                .expectNext(true)
                .verifyComplete();
        Flux<User> fluxWithoutSkyler = Flux.just(JESSE, WALTER);
        Mono<Boolean> expectedFalse = workshop.includesSkyler(fluxWithoutSkyler);
        StepVerifier.create(expectedFalse)
                .expectNext(false)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void doesNotIncludeSkylerTest() {
        Flux<User> fluxWithoutSkyler = Flux.just(JESSE, WALTER);
        Mono<Boolean> expectedTrue = workshop.doesNotIncludeSkyler(fluxWithoutSkyler);
        StepVerifier.create(expectedTrue)
                .expectNext(true)
                .verifyComplete();
        Flux<User> fluxWithSkyler = Flux.just(JESSE, SKYLER, WALTER);
        Mono<Boolean> expectedFalse = workshop.doesNotIncludeSkyler(fluxWithSkyler);
        StepVerifier.create(expectedFalse)
                .expectNext(false)
                .verifyComplete();
    }
}
