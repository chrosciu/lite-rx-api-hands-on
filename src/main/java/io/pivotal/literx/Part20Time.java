package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Part20Time {

//========================================================================================

    // TODO Return the mono which returns its value faster
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.first(mono1, mono2);
    }

//========================================================================================

    // TODO Return the flux which returns the first value faster
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.first(flux1, flux2);
    }

//========================================================================================

    // TODO Return flux which reports error if no user is signalled for two seconds
    Flux<User> fluxWithTwoSecondsTimeout(Flux<User> flux) {
        return flux.timeout(Duration.of(2, ChronoUnit.SECONDS));
    }

}
