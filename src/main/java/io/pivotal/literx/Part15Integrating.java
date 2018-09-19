package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ObservableRepository;
import io.pivotal.literx.service.UserPointsService;
import reactor.core.publisher.Mono;

public class Part15Integrating {

//========================================================================================
    // TODO Sum all points for all users (beware of traps !). Discard users whose sum of points is less than or equal to 5
    public Mono<Integer> sumUsersPoints(ObservableRepository<User> userObservableRepository, UserPointsService userPointsService) {
        return null;
    }
}
