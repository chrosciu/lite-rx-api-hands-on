package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part16GroupTest {

    private Part16Group workshop = new Part16Group();

//========================================================================================

    @Test
    public void takeEachThreeTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER, SAUL, WALTER, SKYLER);
        Flux<List<User>> resultFlux = workshop.takeEachThree(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(Arrays.asList(SKYLER, SAUL, JESSE))
                .expectNext(Arrays.asList(WALTER, SAUL, WALTER))
                .expectNext(Arrays.asList(SKYLER))
                .verifyComplete();
    }

//========================================================================================


    @Test
    public void takeTwoOfEachFiveTest() {
        Flux<User> userFlux = Flux.just(SKYLER, SAUL, JESSE, WALTER, SAUL, WALTER, SKYLER, JESSE, WALTER, SKYLER, JESSE, SAUL, SKYLER);
        Flux<List<User>> resultFlux = workshop.takeTwoOfEachFive(userFlux);
        StepVerifier.create(resultFlux)
                .expectNext(Arrays.asList(SKYLER, SAUL))
                .expectNext(Arrays.asList(WALTER, SKYLER))
                .expectNext(Arrays.asList(JESSE, SAUL))
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void calculateIncrementTest() {
        Flux<Integer> flux = Flux.just(1, 3, 8, 12);
        Flux<Integer> resultFlux = workshop.calculateIncrement(flux);
        StepVerifier.create(resultFlux)
                .expectNext(2, 5, 4)
                .verifyComplete();
    }

//========================================================================================

    @Test
    public void splitIntoLinesTest() {
        String lines = "abc\ndef\ngh";
        Flux<Character> flux = Flux.fromStream(lines.chars().mapToObj(x -> (char)x));
        Flux<String> resultFlux = workshop.splitIntoLines(flux);
        StepVerifier.create(resultFlux)
                .expectNext("abc")
                .expectNext("def")
                .expectNext("gh")
                .verifyComplete();
    }
}
