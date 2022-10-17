package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Learn how to group Flux elements
 */
public class Part09Group {

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

    //TODO Return flux with difference between current element and previous one (an increment)
    //E.g for sequence (1, 3, 8, 12) it should return (2, 5, 4)
    Flux<Integer> calculateIncrement(Flux<Integer> flux) {
        return flux.buffer(2, 1).filter(l -> l.size() > 1).map(l -> l.get(1) - l.get(0));
    }

//========================================================================================

    // TODO Split given stream of characters to separate lines, and return Flux emitting these lines.
    // You can assume that '\n' character can be treated as newline character.
    // These newline characters should not be emitted
    Flux<String> splitIntoLines(Flux<Character> flux) {
        return flux.bufferWhile(c -> !c.equals('\n'))
                .map(c -> c.stream().map(String::valueOf).collect(Collectors.joining()));
    }

}
