package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.event.EventListener;
import io.pivotal.literx.event.EventSource;
import reactor.core.publisher.Flux;

import java.util.Collections;

/**
 * Learn how to create Flux instances programmatically
 */
public class Part12ProgrammaticFlux {

//========================================================================================
    // Helper methods for generating string containing requested number of stars
    String nStars(int n) {
        return String.join("", Collections.nCopies(n, "*"));
    }

//========================================================================================

    // TODO Return Flux containing multiplied stars in given range (inclusive) using Flux#generate
    // E.g if from=3 and to=7 flux should emit following strings
    // ***
    // ****
    // *****
    // ******
    // *******
    // You can use nStars helper function
    Flux<String> starsFluxWithGenerate(int from, int to) {
        return Flux.generate(() -> from, (n, sink) -> {
            sink.next(nStars(n));
            if (n == to) {
                sink.complete();
            }
            return n + 1;
        });
    }

//========================================================================================

    //TODO Return flux containing users emitted by given event source (using Flux#create)
    Flux<User> userFluxFromEventSource(EventSource<User> eventSource) {
        return Flux.push(sink -> {
            eventSource.registerListener(new EventListener<User>() {
                @Override
                public void next(User user) {
                    sink.next(user);
                }
                @Override
                public void error(Exception e) {
                    sink.error(e);
                }
                @Override
                public void end() {
                    sink.complete();
                }
            });
        });
    }
}
