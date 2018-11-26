package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SAUL;
import static io.pivotal.literx.domain.User.SKYLER;
import static io.pivotal.literx.domain.User.WALTER;

public class Part16GroupingTest {

    private Part16Grouping workshop = new Part16Grouping();

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
    public void splitIntoLinesTest() {
        String lines = "abc\ndef\ngh";
        Flux<String> resultFlux = workshop.splitIntoLines(lines);
        StepVerifier.create(resultFlux)
                .expectNext("abc")
                .expectNext("def")
                .expectNext("gh")
                .verifyComplete();
    }



}
