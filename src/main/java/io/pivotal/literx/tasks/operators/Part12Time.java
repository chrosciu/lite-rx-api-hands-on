package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;

public class Part12Time {

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

//========================================================================================

    // TODO Return flux which contains single user emitted after delay in ms
    Flux<User> fluxWithDelayMs(User user, Integer delayMs) {
        return null;
    }


//========================================================================================

    //TODO Create a Flux<String> with items spread over time
    //Items are described by list passed as an argument
    //Each item on list represents one item of result Flux
    //First field of tuple stores item value
    //Second one - delay of this item in milliseconds
    Flux<String> createFluxFromItemsWithDelaysInMs(List<Tuple2<String, Integer>> itemsWithDelays) {
        return null;
    }

//========================================================================================

    //TODO Combine latest elements of given two fluxes, result element should be a concatenation of two inputs
    Flux<String> combineLatestWithConcat(Flux<String> flux1, Flux<String> flux2) {
        return null;
    }


}
