package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;

public class Part21Debug {

    ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return repository.findAll().log();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return repository.findAll()
                .doOnSubscribe(s -> System.out.println("Starring: "))
                .doOnNext(u -> System.out.println(u.getFirstname() + " " + u.getLastname()))
                .doOnComplete(() -> System.out.println("The end!"));
    }

}


