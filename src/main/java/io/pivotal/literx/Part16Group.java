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
        return flux.buffer(3);
    }

//========================================================================================

    // TODO Buffer each five users in a list, take first two and return Flux with these lists
    Flux<List<User>> takeTwoOfEachFive(Flux<User> flux) {
        return flux.buffer(2, 5);
    }

//========================================================================================

    // TODO Split given stream of characters to separate lines, and return Flux emitting these lines.
    // You can assume that '\n' character can be treated as newline character.
    // These newline characters should not be emitted
    Flux<String> splitIntoLines(Flux<Character> flux) {
        return flux.bufferWhile(character -> !character.equals('\n'))
                .map(characters -> characters.stream().reduce("", (s, c) -> s + c, (s1, s2) -> s1 + s2));
    }

}
