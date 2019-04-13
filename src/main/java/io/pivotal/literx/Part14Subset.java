package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

import static io.pivotal.literx.domain.User.JESSE;
import static io.pivotal.literx.domain.User.SKYLER;

/**
 * Learn how to create a subset of Flux elements
 */
public class Part14Subset {

//========================================================================================

    // TODO Take two first users
    Flux<User> takeTwo(Flux<User> flux) {
        return flux.take(2);
    }

//========================================================================================

    // TODO Take last user
    Flux<User> takeLast(Flux<User> flux) {
        return flux.takeLast(1);
    }

//========================================================================================

    // TODO Skip two first users
    Flux<User> skipTwo(Flux<User> flux) {
        return flux.skip(2);
    }

//========================================================================================

    // TODO Take users until user with last name different than White comes
    Flux<User> takeUsersUntilWhite(Flux<User> flux) {
        return flux.takeUntil(user -> !"White".equals(user.getLastname()));
    }

//========================================================================================

    // TODO Skip users while they are SKYLER or JESSE
    Flux<User> skipUsersWhileEqualToSkylerOrJesse(Flux<User> flux) {
        return flux.skipWhile(user -> SKYLER.equals(user) || JESSE.equals(user));
    }

}
