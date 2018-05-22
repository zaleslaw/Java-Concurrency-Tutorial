package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * This actor handle messages from inbox and reply it
 * But if somebody send him 9, he stops himself
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
                .match(Integer.class, i -> {
                    if(i==9){
                        getContext().stop(this.getSelf());
                    }
                })
                .matchAny(o -> log.info(this.self().toString() + " received unknown message"))
                .build();
    }
}
