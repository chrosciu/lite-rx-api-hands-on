package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part14SubsetTest {

    private Part14Subset workshop = new Part14Subset();

//========================================================================================

    @Test
    public void takeTwoTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.takeTwo(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SKYLER, SAUL)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void takeLastTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.takeLast(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(WALTER)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void skipTwoTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.skipTwo(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(JESSE, WALTER)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void takeUsersUntilWhiteTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.takeUsersUntilWhite(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SKYLER, SAUL)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void skipUsersWhileEqualToSkylerOrJesseTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<User> resultFlux = workshop.skipUsersWhileEqualToSkylerOrJesse(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(SAUL, JESSE, WALTER)
                .verifyComplete();
    }

}
