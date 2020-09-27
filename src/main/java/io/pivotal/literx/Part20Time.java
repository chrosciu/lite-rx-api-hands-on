package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

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

//========================================================================================

    //TODO Create a Flux<String> with items spread over time
    //Items are described by list passed as an argument
    //Each item on list represents one item of result Flux
    //First field of tuple stores item value
    //Second one - delay of this item in milliseconds
    Flux<String> createFluxFromItemsWithDelaysInMs(List<Tuple2<String, Integer>> itemsWithDelays) {
        Flux<String> flux = Flux.empty();
        for (Tuple2<String, Integer> itemWithDelay: itemsWithDelays) {
            Flux<String> f = Flux.just(itemWithDelay.getT1()).delaySequence(Duration.ofMillis(itemWithDelay.getT2()));
            flux = flux.mergeWith(f);
        }
        return flux;
    }

//========================================================================================

    //TODO Combine latest elements of given two fluxes, result element should be a concatenation of two inputs
    Flux<String> combineLatestWithConcat(Flux<String> flux1, Flux<String> flux2) {
        return Flux.combineLatest(flux1, flux2, (s1, s2) -> s1 + s2);
    }


}
