package Chapter_10_Akka;

import Chapter_10_Akka.actors.FilteredActor;
import Chapter_10_Akka.actors.ParentActor;
import akka.actor.*;

import java.util.concurrent.TimeoutException;

/**
 * Juliet asked Romeo "Are you Montekki?"
 * Feelings are burnt in Romeo's heart
 * Feelings are sent to Juliet
 * Feelings are rejected by Juliet
 * Romeo dies
 * Feelings dies with him (and before him)
 */
public class Ex_9_Was_born_to_love {
    public static void main(String[] args) throws TimeoutException {

        ActorSystem system = ActorSystem.create("MathLaboratory");

        ActorRef romeo = system.actorOf(Props
                        .create(ParentActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Romeo");

        ActorRef juliet = system.actorOf(Props
                        .create(FilteredActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Juliet");

        romeo.tell(ParentActor.ARE_YOU_MONTEKKI, juliet);
        romeo.tell(ParentActor.LIFE_IS_FINISHED, ActorRef.noSender());

    }
}
