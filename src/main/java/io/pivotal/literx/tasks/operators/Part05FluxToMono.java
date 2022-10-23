package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part05FluxToMono {

    //========================================================================================

    //TODO Build a Mono containing 3rd item of the given Flux and
    Mono<User> monoFromFluxThirdItem(Flux<User> flux) {
        return null;
    }

    //========================================================================================

    //TODO Transform given Flux to Mono assuming that it has no more than one item
    //In case of empty Flux a Mono with SKYLER user should be returned
    Mono<User> monoFromFluxWithOneItemOrSkyler(Flux<User> flux) {
        return null;
    }

    //========================================================================================

    //TODO Transform given Flux to Mono assuming that it has no more than one item
    //In case of empty Flux a empty Mono should be returned
    Mono<User> monoFromFluxWithOneItemOrEmpty(Flux<User> flux) {
        return null;
    }
}
