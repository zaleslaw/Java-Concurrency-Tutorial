package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * This actor should think longer to forward somebody his message
 */
public class AngryForwarder extends AbstractActor {
    public static final String FORWARD_NEXT_MESSAGE = "Forward next message";
    public static final String HANDLE_NEXT_MESSAGE = "Handle next message";
    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);
    private boolean isForwardNextMessage;
    private ActorRef childHandler =   getContext().actorOf(Props.create(OutspokenActor.class), "Iago");
    private int tries;


    // Actor recursion between parent and child when forwarding is enabled
    // Parent -> child -> parent -> child ...
    // private ActorRef childHandler =   getContext().actorOf(Props.create(AnsweredActor.class), "Iago");

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .matchEquals(FORWARD_NEXT_MESSAGE, this::enableForwarding)
                .matchEquals(HANDLE_NEXT_MESSAGE, this::disableForwarding)
                .matchEquals("Give me the Iago path!", this::giveChildPath)
                .match(String.class, this::processMessage)
                .match(Integer.class, this::handleShortCommand)
                .build();
    }

    private void giveChildPath(String something) {
        this.getSender().tell(childHandler.path().toString(), this.getSelf());
    }

    private void handleShortCommand(int i) {
        switch(i) {
            case 9: getContext().stop(this.getSelf()); break;
            default:  log.info("Received a message: {}", i);
        }

    }

    private void processMessage(String s) {
        if(isForwardNextMessage){
            childHandler.forward(s, getContext());
        } else {
            if(getSender().toString().contains("Desdemona") && tries < 10){
                this.getSender().tell("O, ay; as summer flies are in the shambles,", this.getSelf());
                tries++;
            }
            log.info("Received String message: {} from {}", s, this.sender());
        }
    }

    private void disableForwarding(String something) {
        isForwardNextMessage = false;
    }

    private void enableForwarding(String something) {
        isForwardNextMessage = true;
    }
}
