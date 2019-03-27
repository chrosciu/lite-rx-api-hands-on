package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Part21DebugTest {

    Part21Debug workshop = new Part21Debug();

//========================================================================================

    @Test
    public void experimentWithLog() {
        Flux<User> flux = workshop.fluxWithLog();
        StepVerifier.create(flux, 0)
                .thenRequest(1)
                .expectNextMatches(u -> true)
                .thenRequest(1)
                .expectNextMatches(u -> true)
                .thenRequest(2)
                .expectNextMatches(u -> true)
                .expectNextMatches(u -> true)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void experimentWithDoOn() {
        Flux<User> flux = workshop.fluxWithDoOnPrintln();
        StepVerifier.create(flux)
                .expectNextCount(4)
                .verifyComplete();
    }

}
