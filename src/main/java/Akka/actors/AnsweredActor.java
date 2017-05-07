package Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * This actor handle messages from Romeo only and answer him
 */
public class AnsweredActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    this.getSender().tell("Who are you? You sent me a missage: '" + s + "'", this.self());
                    log.info("Received String message: {}", s);

                })
                .matchAny(o -> log.info(this.self().toString() + " received unknown message"))
                .build();
    }
}
