package io.pivotal.literx.tasks.operators;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;

/**
 * Learn how to create a subset of Flux elements
 */
public class Part05Subset {

//========================================================================================

    // TODO Take two first users
    Flux<User> takeTwo(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Take last user
    Flux<User> takeLast(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Skip two first users
    Flux<User> skipTwo(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Take users until user with last name different than "White" comes
    Flux<User> takeUsersUntilWhite(Flux<User> flux) {
        return null;
    }

//========================================================================================

    // TODO Skip users while they are SKYLER or JESSE
    Flux<User> skipUsersWhileEqualToSkylerOrJesse(Flux<User> flux) {
        return null;
    }

}

