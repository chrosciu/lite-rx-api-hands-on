package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Part12Time {

//========================================================================================

    // TODO Return the mono which returns its value faster
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        return Mono.firstWithSignal(mono1, mono2);
    }

//========================================================================================

    // TODO Return the flux which returns the first value faster
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        return Flux.firstWithSignal(flux1, flux2);
    }

//========================================================================================

    // TODO Return flux which reports error if no user is signalled for two seconds
    Flux<User> fluxWithTwoSecondsTimeout(Flux<User> flux) {
        return flux.timeout(Duration.of(2, ChronoUnit.SECONDS));
    }

//========================================================================================

    // TODO Return flux which contains single user emitted after delay in ms
    Flux<User> fluxWithDelayMs(User user, Integer delayMs) {
        return Flux.just(user).delayElements(Duration.ofMillis(delayMs));
    }


//========================================================================================

    //TODO Create a Flux<String> with items spread over time
    //Items are described by list passed as an argument
    //Each item on list represents one item of result Flux
    //First field of tuple stores item value
    //Second one - delay of this item in milliseconds
    Flux<String> createFluxFromItemsWithDelaysInMs(List<Tuple2<String, Integer>> itemsWithDelays) {
        return Flux.fromIterable(itemsWithDelays)
                .flatMap(t -> Mono.just(t.getT1()).delayElement(Duration.ofMillis(t.getT2())));
    }

//========================================================================================

    //TODO Combine latest elements of given two fluxes, result element should be a concatenation of two inputs
    Flux<String> combineLatestWithConcat(Flux<String> flux1, Flux<String> flux2) {
        return Flux.combineLatest(flux1, flux2, (s1, s2) -> s1 + s2);
    }


}
