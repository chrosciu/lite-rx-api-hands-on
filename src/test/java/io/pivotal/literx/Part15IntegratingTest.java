package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ObservableRepository;
import io.pivotal.literx.repository.ObservableUserRepository;
import io.pivotal.literx.service.UserPointsService;
import io.pivotal.literx.service.UserPointsServiceImpl;
import org.junit.Test;
import reactor.test.StepVerifier;

public class Part15IntegratingTest {

    private Part15Integrating workshop = new Part15Integrating();
    private ObservableRepository<User> userObservableRepository = new ObservableUserRepository();
    private UserPointsService userPointsService = new UserPointsServiceImpl();

//========================================================================================

  @Test
  public void sumUsersPoints() {
      final int expectedSum = 108;
      StepVerifier.create(workshop.sumUsersPoints(userObservableRepository, userPointsService))
              .expectNext(expectedSum)
              .verifyComplete();
  }

}
