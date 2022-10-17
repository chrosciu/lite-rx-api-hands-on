package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class Part06CollectTest {

    private Part06Collect workshop = new Part06Collect();

//========================================================================================

    @Test
    public void collect() {
        ReactiveRepository<User> repository = new ReactiveUserRepository();
        Mono<List<User>> collection = workshop.fluxCollection(repository.findAll());
        StepVerifier.create(collection)
                .expectNext(Arrays.asList(User.SKYLER, User.JESSE, User.WALTER, User.SAUL))
                .verifyComplete();
    }


}
