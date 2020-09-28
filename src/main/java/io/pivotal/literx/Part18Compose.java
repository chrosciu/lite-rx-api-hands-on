package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * Learn how to compose Reactor operators into new ones
 */
public class Part18Compose {

//========================================================================================

    // TODO Create operator that returns name and last name (joined with space) of user
    // (both upper case) but skips these with last name White
    Function<Flux<User>, Flux<String>> nameUpperCase() {
        return userFlux -> userFlux
                .filter(u -> !"White".equals(u.getLastname()))
                .map(user -> user.getFirstname() + " " + user.getLastname())
                .map(String::toUpperCase);
    }

//========================================================================================

    // TODO Create operator that returns name and last name of user (joined with space)
    // Name should made upper case for each even subscriber
    // and lower case for each odd one
    // Hint: this operator will be called for each subscriber as Flux#transformDeferred is being used here
    Function<Flux<User>, Flux<String>> nameUpperOrLowerCase() {
        final AtomicBoolean even = new AtomicBoolean(true);
        return userFlux -> {
            even.set(!even.get());
            Flux<String> names = userFlux.map(user -> user.getFirstname() + " " + user.getLastname());
            if (even.get()) {
                return names.map(String::toLowerCase);
            } else {
                return names.map(String::toUpperCase);
            }
        };
    }
}
