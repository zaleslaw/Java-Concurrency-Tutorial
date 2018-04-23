package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class ParentActor extends AbstractActor {
    public static final String ARE_YOU_MONTEKKI = "Are you Montekki?";
    public static final String LIFE_IS_FINISHED = "Life is finished";
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("Actor was born: {}", this.getSelf());
        getContext().actorOf(Props.create(ChildActor.class), "feelings");
    }

    @Override
    public void postStop(){
        log.info("Actor was died: {}", this.getSelf());
    }


    /*
    Please note that the Akka Actor receive message loop is exhaustive, which is different compared to Erlang and the late Scala Actors. This means that you need to provide a pattern match for all messages that it can accept and if you want to be able to handle unknown messages then you need to have a default case as in the example above. Otherwise an akka.actor.UnhandledMessage(message, sender, recipient) will be published to the ActorSystem's EventStream.
     */
    @Override
    public Receive createReceive() {
        ReceiveBuilder builder = ReceiveBuilder.create();

        handleFirstQuestion(builder) // first pattern = war with builders
                .matchEquals(LIFE_IS_FINISHED, this::lifeIsFinished) // method reference
                .matchAny(o -> log.info(this.self().toString() + " received unknown message")); // lambda for each handler

        return  builder.build();
    }

    private void lifeIsFinished(String s) {
        getContext().stop(this.getSelf());
    }


    private ReceiveBuilder handleFirstQuestion(ReceiveBuilder builder) {
        return builder.matchEquals(ARE_YOU_MONTEKKI, s -> {
            this.getSender().tell("I'm a lover", getContext().findChild("feelings").get());
            log.info("Received String message: {}", s);
        });
    }
}
