package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part05FluxToMonoTest {

    private Part05FluxToMono workshop = new Part05FluxToMono();

    //========================================================================================

    @Test
    public void monoFromFluxThirdItemTest() {
        Flux<User> flux = Flux.just(JESSE, SAUL, SKYLER, WALTER);
        StepVerifier.create(workshop.monoFromFluxThirdItem(flux))
                .expectNext(SKYLER)
                .verifyComplete();

        Flux<User> shortFlux = Flux.just(JESSE, SAUL);
        StepVerifier.create(workshop.monoFromFluxThirdItem(shortFlux))
                .verifyError(IndexOutOfBoundsException.class);
    }

    //========================================================================================

    @Test
    public void monoFromFluxWithOneItemOrSkylerTest() {
        Flux<User> flux = Flux.just(JESSE);
        StepVerifier.create(workshop.monoFromFluxWithOneItemOrSkyler(flux))
                .expectNext(JESSE)
                .verifyComplete();

        Flux<User> emptyFlux = Flux.empty();
        StepVerifier.create(workshop.monoFromFluxWithOneItemOrSkyler(emptyFlux))
                .expectNext(SKYLER)
                .verifyComplete();
    }

    //========================================================================================

    @Test
    public void monoFromFluxWithOneItemOrEmptyTest() {
        Flux<User> flux = Flux.just(JESSE);
        StepVerifier.create(workshop.monoFromFluxWithOneItemOrEmpty(flux))
                .expectNext(JESSE)
                .verifyComplete();

        Flux<User> emptyFlux = Flux.empty();
        StepVerifier.create(workshop.monoFromFluxWithOneItemOrEmpty(emptyFlux))
                .verifyComplete();
    }

}
