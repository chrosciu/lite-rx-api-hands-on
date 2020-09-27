package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;


/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperationsTest {

	Part08OtherOperations workshop = new Part08OtherOperations();

//========================================================================================

	@Test
	public void complete() {
		ReactiveRepository<User> repository = new ReactiveUserRepository();
		PublisherProbe<User> probe = PublisherProbe.of(repository.findAll());
		Mono<Void> completion = workshop.fluxCompletion(probe.flux());
		StepVerifier.create(completion)
				.verifyComplete();
		probe.assertWasRequested();
	}

//========================================================================================

	@Test
	public void nullHandling() {
		Mono<User> mono = workshop.nullAwareUserToMono(User.SKYLER);
		StepVerifier.create(mono)
				.expectNext(User.SKYLER)
				.verifyComplete();
		mono = workshop.nullAwareUserToMono(null);
		StepVerifier.create(mono)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void emptyHandling() {
		Mono<User> mono = workshop.emptyToSkyler(Mono.just(User.WALTER));
		StepVerifier.create(mono)
				.expectNext(User.WALTER)
				.verifyComplete();
		mono = workshop.emptyToSkyler(Mono.empty());
		StepVerifier.create(mono)
				.expectNext(User.SKYLER)
				.verifyComplete();
	}

}

