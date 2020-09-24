package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part18ComposeTest {

    private Part18Compose workshop = new Part18Compose();

//========================================================================================

    @Test
    public void nameUpperCaseTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<String> namesFlux = userFlux.transform(workshop.nameUpperCase());
        StepVerifier.create(namesFlux)
                .expectNext("SAUL GOODMAN", "JESSE PINKMAN")
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void nameUpperOrLowerCaseTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER);
        Flux<String> namesFlux = userFlux.transformDeferred(workshop.nameUpperOrLowerCase());
        StepVerifier.create(namesFlux)
                .expectNext("SKYLER WHITE", "SAUL GOODMAN", "JESSE PINKMAN", "WALTER WHITE")
                .verifyComplete();
        StepVerifier.create(namesFlux)
                .expectNext("skyler white", "saul goodman", "jesse pinkman", "walter white")
                .verifyComplete();
        StepVerifier.create(namesFlux)
                .expectNext("SKYLER WHITE", "SAUL GOODMAN", "JESSE PINKMAN", "WALTER WHITE")
                .verifyComplete();
    }
}
