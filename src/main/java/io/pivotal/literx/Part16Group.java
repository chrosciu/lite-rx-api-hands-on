package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

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

    // TODO Split given string to separate lines, and return Flux emitting these lines.
    // Newline character should not be emitted
    Flux<String> splitIntoLines(String string) {
        Stream<Character> characterStream = string.chars().mapToObj(x -> (char)x);
        return Flux.fromStream(characterStream)
                .bufferWhile(character -> !character.equals('\n'))
                .map(characters -> characters.stream().reduce("", (s, c) -> s + c, (s1, s2) -> s1 + s2));
    }

}
