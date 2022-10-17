package io.pivotal.literx.tasks.operators;

import reactor.core.publisher.Flux;

public class Part03Decorate {

//========================================================================================

    //TODO Prefix element with index (1-based) in stream in format `index: element`
    Flux<String> decorateWithIndexOneBased(Flux<String> flux) {
        return flux.index((index, s) -> String.format("%d: %s", index + 1, s));
    }

}
