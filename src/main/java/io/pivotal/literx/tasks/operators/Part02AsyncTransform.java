package io.pivotal.literx.tasks.operators;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;

public class Part02AsyncTransform {

    private final static int NUMBER_OF_GENERATED_ITEMS = 3;
    private final static long INTERVAL_OF_GENERATED_ITEMS_IN_MS = 100;

    // Repeat given string n times
    private String repeatString(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }

    // Generate sequence of n string elements with given interval in milliseconds
    // Then n-th item (counted from 1) is initial string s repeated n times
    private Flux<String> generateSequenceWithInterval(String s, int n, long intervalMs) {
        return Flux.interval(Duration.ZERO, Duration.ofMillis(intervalMs)).map(i -> repeatString(s, i.intValue() + 1)).take(n);
    }

    private Flux<String> asyncTransformFunc(String s) {
        return generateSequenceWithInterval(s, NUMBER_OF_GENERATED_ITEMS, INTERVAL_OF_GENERATED_ITEMS_IN_MS);
    }

//========================================================================================

    // TODO Transform all items on source Flux using #asyncTransformFunc and return result Flux
    Flux<String> transform(Flux<String> items) {
        return items.flatMap(this::asyncTransformFunc);
    }

//========================================================================================

    // TODO Transform all items on source Flux using #asyncTransformFunc and return result Flux
    // Result flux should emit all transformed items for first source item, then all transformed items for second source item and so on
    Flux<String> transformWithOrder(Flux<String> items) {
        return items.concatMap(this::asyncTransformFunc);
    }

//========================================================================================

    // TODO Transform all items on source Flux using #asyncTransformFunc and return result Flux
    // Result flux should abandon emitting items for given source item if next source item is emitted
    Flux<String> transformUntilNext(Flux<String> items) {
        return items.switchMap(this::asyncTransformFunc);
    }

}
