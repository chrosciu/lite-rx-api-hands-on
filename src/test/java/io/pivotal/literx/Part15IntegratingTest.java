package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.IterableRepository;
import io.pivotal.literx.repository.IterableUserRepository;
import io.pivotal.literx.service.UserPointsService;
import io.pivotal.literx.service.UserPointsServiceImpl;
import org.junit.Test;
import reactor.test.StepVerifier;

public class Part15IntegratingTest {

    private Part15Integrating workshop = new Part15Integrating();
    private IterableRepository<User> iterableUserRepository = new IterableUserRepository();
    private UserPointsService userPointsService = new UserPointsServiceImpl();

//========================================================================================

  @Test
  public void sumUsersPoints() {
      final int expectedSum = 108;
      StepVerifier.create(workshop.sumUsersPoints(iterableUserRepository, userPointsService))
              .expectNext(expectedSum)
              .verifyComplete();
  }

}
