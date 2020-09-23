package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part25DecorateTest {

    private Part25Decorate workshop = new Part25Decorate();

    @Test
    public void decorateWithIndexOneBasedTest() {
        Flux<String> flux = Flux.just(JESSE, SKYLER, WALTER, SAUL).map(User::getFirstname);
        StepVerifier.create(workshop.decorateWithIndexOneBased(flux))
                .expectNext("1: Jesse")
                .expectNext("2: Skyler")
                .expectNext("3: Walter")
                .expectNext("4: Saul")
                .verifyComplete();
    }

}
