package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part20Time {

//========================================================================================

    // TODO Return the mono which returns its value faster
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return null;
    }

//========================================================================================

    // TODO Return the flux which returns the first value faster
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return null;
    }

//========================================================================================

    // TODO Return flux which reports error if no user is signalled for two seconds
    Flux<User> fluxWithTwoSecondsTimeout(Flux<User> flux) {
        return null;
    }

}
