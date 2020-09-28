package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.event.ErrorUserEventSource;
import io.pivotal.literx.event.EventSource;
import io.pivotal.literx.event.UserEventSource;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part12ProgrammaticFluxTest {

    Part12ProgrammaticFlux workshop = new Part12ProgrammaticFlux();
    EventSource<User> eventSource = new UserEventSource();
    EventSource<User> eventSourceWithError = new ErrorUserEventSource();

//========================================================================================

    @Test
    public void starsWithGenerate() {
        Flux<String> flux = workshop.starsFluxWithGenerate(1, 20);

        List<String> expectedSequence = IntStream.rangeClosed(1, 20)
                .mapToObj(workshop::nStars)
                .collect(Collectors.toList());

        StepVerifier.create(flux)
                .expectNextSequence(expectedSequence)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void usersFromEventSource() {
        Flux<User> flux = workshop.userFluxFromEventSource(eventSource);
        List<User> expectedSequence = Arrays.asList(WALTER, SAUL, SKYLER, JESSE);

        StepVerifier.create(flux)
                .expectNextSequence(expectedSequence)
                .verifyComplete();

        Flux<User> fluxWithError = workshop.userFluxFromEventSource(eventSourceWithError);

        StepVerifier.create(fluxWithError)
                .expectError(RuntimeException.class)
                .verify();
    }


}
