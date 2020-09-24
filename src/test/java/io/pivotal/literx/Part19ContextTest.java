package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

import static io.pivotal.literx.domain.User.SKYLER;

public class Part19ContextTest {

    private Part19Context workshop = new Part19Context();

    private static final String SOME_KEY = "foo";
    private static final String SOME_VALUE = "bar";
    private static final String DUMMY = "dummy";

//========================================================================================

    @Test
    public void logUserFirstNameTest() {
        Flux<User> flux = Flux.just(SKYLER);
        Flux<String> logs = workshop.logUserFirstName(flux, SOME_KEY, DUMMY);
        StepVerifier.create(logs)
                .expectNext(DUMMY + " Skyler")
                .verifyComplete();
        StepVerifier.create(logs, StepVerifierOptions.create().withInitialContext(Context.of(SOME_KEY, SOME_VALUE)))
                .expectNext(SOME_VALUE + " Skyler")
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void fillUsersWithContextTest() {
        Flux<User> flux = Flux.just(SKYLER);
        Flux<User> filledFlux = workshop.fillUsersWithContext(flux, SOME_KEY, SOME_VALUE);
        StepVerifier.create(filledFlux)
                .expectAccessibleContext()
                .contains(SOME_KEY, SOME_VALUE)
                .then()
                .expectNext(SKYLER)
                .verifyComplete();
    }

}
