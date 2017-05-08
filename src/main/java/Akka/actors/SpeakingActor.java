package Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SpeakingActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                // pattern-matching for Java

                .match(String.class, s -> {
                    log.info("Received String message: {}", s);
                })
                .match(Integer.class, i -> {
                    log.info("Received Integer message: {}", i);
                })
                .matchAny(o -> log.info("Received unknown message"))
                .build();
    }
}
