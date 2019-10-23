package io.pivotal.literx;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;

public class Part24CombineLatest {

//========================================================================================

    //TODO Create a flux with items described as list of elements with delays in milliseconds
    Flux<String> createFluxFromItemsWithDelaysInMs(List<Tuple2<String, Integer>> itemsWithDelays) {
        return null;
    }

//========================================================================================

    //TODO Combine latest elements of given two fluxes, result element should be a concatenation of two inputs
    Flux<String> combineLatestWithConcat(Flux<String> flux1, Flux<String> flux2) {
        return null;
    }

}
