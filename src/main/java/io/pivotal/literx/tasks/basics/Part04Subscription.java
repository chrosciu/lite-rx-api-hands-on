package io.pivotal.literx.tasks.basics;

import org.reactivestreams.Subscriber;

public class Part04Subscription {

//========================================================================================

    // TODO Write a subscriber that requests item in batches of #{batchSize}
    // and cancels subscription if number of received items reaches #{maxAmount}
    Subscriber<Integer> createBatchIntSubscriber(int batchSize, int maxAmount) {
        return null;
    }

}
