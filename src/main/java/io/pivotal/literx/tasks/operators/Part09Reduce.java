package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.pivotal.literx.domain.User.SKYLER;

/**
 * Learn how to reduce all elements of Flux int one Mono
 */
public class Part09Reduce {

//========================================================================================

    // TODO Concat all strings included in Flux
    Mono<String> concatStrings(Flux<String> flux) {
        return flux.reduce((s, s2) -> s + s2);
    }

//========================================================================================

    // TODO Signal true if any of user in flux is SKYLER
    Mono<Boolean> includesSkyler(Flux<User> flux) {
        return flux.any(SKYLER::equals);
    }

//========================================================================================

    // TODO Signal true if none of user in flux is SKYLER
    Mono<Boolean> doesNotIncludeSkyler(Flux<User> flux) {
        return flux.all(u -> !SKYLER.equals(u));
    }
}
