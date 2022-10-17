package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part06Trigger {

    //========================================================================================

    //TODO Return Flux which is triggered when given stream completes
    <T> Flux<T> triggerNewStreamWhenStreamCompletes(Flux<?> stream, Flux<T> newStream) {
        return stream.thenMany(newStream);
    }

    //========================================================================================

    //TODO Return Mono<Void> which completes when given stream completes
    Mono<Void> triggerCompleteWhenStreamCompletes(Flux<?> stream) {
        return stream.then();
    }

    //========================================================================================

    //TODO Return Mono<Void> which completes when all given streams complete
    Mono<Void> triggerCompleteWhenAllStreamsComplete(Flux<?>... streams) {
        return Mono.when(streams);
    }

    //========================================================================================

    //TODO Return alternate Flux containing SKYLER and JESSE users if given stream is empty
    Flux<User> returnFluxWithSkylerAndJesseWhenStreamIsEmpty(Flux<User> flux) {
        return flux.switchIfEmpty(Flux.just(User.SKYLER, User.JESSE));
    }

}
