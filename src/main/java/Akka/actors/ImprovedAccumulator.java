package Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ImprovedAccumulator extends AbstractActor {
    public static final String GET_THE_SUM = "get the sum";
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private int sum;


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Integer.class, i -> {
                    log.info("Received Integer message: {}", i);
                    sum += i;

                })
                .match(String.class, s -> {
                    if (s.equals(GET_THE_SUM)) {
                        log.info("The sum is {}", sum);
                        this.getSender().tell(sum, this.self());
                    }
                })
                .matchAny(o -> log.info("received unknown message"))
                .build();
    }
}