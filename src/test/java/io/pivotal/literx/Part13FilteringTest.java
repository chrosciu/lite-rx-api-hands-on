package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part13FilteringTest {

    private Part13Filtering workshop = new Part13Filtering();

//========================================================================================

    @Test
    public void removeUsersWithWhiteName() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.removeUsersWithWhiteName(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SAUL, JESSE)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void removeDuplicatedUsers() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, SKYLER, JESSE, WALTER);
        Flux<User> resultFlux = workshop.removeDuplicatedUsers(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SKYLER, SAUL, JESSE, WALTER)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void removeUsersWithDuplicatedNames() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, SKYLER, JESSE, WALTER);
        Flux<User> resultFlux = workshop.removeUsersWithDuplicatedNames(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SKYLER, SAUL, JESSE)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void removeDuplicatedUsersInSequences() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, SAUL, JESSE, JESSE, WALTER);
        Flux<User> resultFlux = workshop.removeDuplicatedUsersInSequences(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SKYLER, SAUL, JESSE, WALTER)
                .verifyComplete();
    }
}
