package io.pivotal.literx;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01FluxTest {

	Part01Flux workshop = new Part01Flux();

//========================================================================================

	@Test
	public void empty() {
		Flux<String> flux = workshop.emptyFlux();

		StepVerifier.create(flux)
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void fromValues() {
		Flux<String> flux = workshop.fooBarFluxFromValues();
		StepVerifier.create(flux)
				.expectNext("foo", "bar")
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void fromList() {
		Flux<String> flux = workshop.fooBarFluxFromList();
		StepVerifier.create(flux)
				.expectNext("foo", "bar")
				.verifyComplete();
	}

//========================================================================================

	@Test
	public void error() {
		Flux<String> flux = workshop.errorFlux();
		StepVerifier.create(flux)
				.verifyError(IllegalStateException.class);
	}

//========================================================================================

	@Test
	public void countEach100ms() {
		StepVerifier.Step<Long> sv = StepVerifier.withVirtualTime(() -> workshop.counter())
				.expectSubscription();
		for (long i = 0; i < 10; i ++)  {
			sv = sv.expectNoEvent(Duration.ofMillis(100))
					.expectNext(i);
		}
		sv.verifyComplete();
	}

}
