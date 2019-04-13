package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/**
 * Learn how to use context in Reactor operators
 */
public class Part19Context {

//========================================================================================

    // TODO Return flux that logs each user first name with prefix (followed by space) taken from given context key
    // If context does not contain such key, given default value should be used instead
    Flux<String> logUserFirstName(Flux<User> flux, String key, String defaultValue) {
        return flux.flatMap(user -> Mono.subscriberContext()
                .map(ctx -> ctx.getOrDefault(key, defaultValue) + " " + user.getFirstname()));
    }

//========================================================================================

    // TODO Fill given flux with context containing given value under given key and return filled flux
    Flux<User> fillUsersWithContext(Flux<User> flux, String key, String value) {
        return flux.subscriberContext(Context.of(key, value));
    }
}
