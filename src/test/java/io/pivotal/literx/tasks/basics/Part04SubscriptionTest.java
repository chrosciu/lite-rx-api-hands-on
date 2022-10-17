package io.pivotal.literx.tasks.basics;

import io.pivotal.literx.tasks.basics.Part04Subscription;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.test.publisher.TestPublisher;

public class Part04SubscriptionTest {

    Part04Subscription workshop = new Part04Subscription();

    @Test
    public void subscriptionAndCancellationTest() {
        TestPublisher<Integer> publisher = TestPublisher.create();
        Flux<Integer> flux = publisher.flux().log();
        Subscriber<Integer> batchIntSubscriber = workshop.createBatchIntSubscriber(2, 7);
        flux.subscribe(batchIntSubscriber);
        publisher.assertSubscribers(1);
        publisher.assertMinRequested(2);
        publisher.assertMaxRequested(2);
        publisher.next(1, 2, 3);
        publisher.assertSubscribers(1);
        publisher.assertMinRequested(1);
        publisher.assertMaxRequested(1);
        publisher.next(4, 5, 6);
        publisher.assertSubscribers(1);
        publisher.assertMinRequested(2);
        publisher.assertMaxRequested(2);
        publisher.next(7, 8, 9);
        publisher.assertCancelled();
        publisher.assertNoSubscribers();
    }
}
