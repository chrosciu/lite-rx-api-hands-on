package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.IterableRepository;
import io.pivotal.literx.service.UserPointsService;
import reactor.core.publisher.Mono;

public class Part17Integrating {

//========================================================================================
    // TODO Sum all points for all users (beware of traps !). Discard points which value is less than or equal to 5
    public Mono<Integer> sumUsersPoints(IterableRepository<User> iterableUserRepository, UserPointsService userPointsService) {
        return null;
    }
}
