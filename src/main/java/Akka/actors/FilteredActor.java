package Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * This actor handle messages from Romeo only and answer him
 */
public class FilteredActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    if(this.getSender().path().name().equals("Romeo")){
                        this.getSender().tell("Romeo, O, Romeo!", this.self());
                        log.info("Received String message: {}", s);
                    } else {
                        log.info("Rejected message: {}", s);
                    }
                })
                .matchAny(o -> log.info(this.self().toString() + " received unknown message"))
                .build();
    }
}
