package io.pivotal.literx;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

public class Part23Subscribe {

//========================================================================================

    // TODO Write a subscriber that requests item in batches of #{batchSize}
    // and cancels subscription if number of received items reaches #{maxAmount}
    Subscriber<Integer> createBatchIntSubscriber(int batchSize, int maxAmount) {
        return new BatchIntSubscriber(batchSize, maxAmount);
    }

    private class BatchIntSubscriber extends BaseSubscriber<Integer> {
        private final int batchSize;
        private final int maxAmount;

        private int received = 0;

        private BatchIntSubscriber(int batchSize, int maxAmount) {
            this.batchSize = batchSize;
            this.maxAmount = maxAmount;
            if (batchSize <= 0 || maxAmount <= 0) {
                throw new IllegalArgumentException("batchSize and maxAmount must be greater than 0");
            }
        }

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            request(batchSize);
        }

        @Override
        protected void hookOnNext(Integer value) {
            ++received;
            if (received >= maxAmount) {
                cancel();
            }
            if (received % batchSize == 0) {
                request(batchSize);
            }
        }
    }

}
