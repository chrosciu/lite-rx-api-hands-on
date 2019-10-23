package io.pivotal.literx;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;

public class Part24CombineLatest {

//========================================================================================

    //TODO Create a flux with items described as list of elements with delays in milliseconds
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
