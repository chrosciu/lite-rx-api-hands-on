package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Part26Collect {

//========================================================================================

    // TODO Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values
    Mono<List<User>> fluxCollection(Flux<User> flux) {
        return flux.collectList(); // TO BE REMOVED
    }



}
