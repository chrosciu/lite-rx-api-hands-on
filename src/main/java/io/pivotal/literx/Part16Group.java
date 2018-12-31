package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * Learn how to group Flux elements
 */
public class Part16Group {

//========================================================================================

    // TODO Buffer each three users in a list and return Flux with these lists
    Flux<List<User>> takeEachThree(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Buffer each five users in a list, take first two and return Flux with these lists
    Flux<List<User>> takeTwoOfEachFive(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Split given string to separate lines, and return Flux emitting these lines.
    // Newline character should not be emitted
    Flux<String> splitIntoLines(String string) {
        return null;
    }

}
