package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class OutspokenActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    log.info("Received String message: {} from {}", s, this.sender());
                })
                .matchAny(o -> log.info(this.self() + " received unknown message"))
                .build();
    }
}
