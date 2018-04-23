package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class ChildActor extends AbstractActor {
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("Child Actor was born: {}", this.getSelf());
    }

    @Override
    public void postStop(){
        log.info("Child Actor was died: {}", this.getSelf());
    }


    /*
    Please note that the Akka Actor receive message loop is exhaustive, which is different compared to Erlang and the late Scala Actors. This means that you need to provide a pattern match for all messages that it can accept and if you want to be able to handle unknown messages then you need to have a default case as in the example above. Otherwise an akka.actor.UnhandledMessage(message, sender, recipient) will be published to the ActorSystem's EventStream.
     */
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
