package Akka;

import Akka.actors.AnsweredActor;
import Akka.actors.FilteredActor;
import Akka.actors.ImprovedAccumulator;
import Akka.actors.OutspokenActor;
import akka.actor.*;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class Ex_7_Inbox_for_two_dead_lovers {
    public static void main(String[] args) throws TimeoutException {

        ActorSystem system = ActorSystem.create("MathLaboratory");

        ActorRef romeo = system.actorOf(Props
                        .create(AnsweredActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Romeo");

        ActorRef juliet = system.actorOf(Props
                        .create(FilteredActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Juliet");



        final Inbox julietInbox = Inbox.create(system);
        julietInbox.send(juliet, "Go to the balcony!");
        julietInbox.send(juliet, "Where are you, dear?");

        final Inbox romeoInbox = Inbox.create(system);
        romeoInbox.send(romeo, "Go to under the balcony!");
        romeoInbox.send(romeo, "I kill you, Montekki!");


        try {
            System.out.println("The message for Juliet is " + julietInbox.receive(Duration.create(1, TimeUnit.SECONDS)));
        } catch(java.util.concurrent.TimeoutException e) {
            //Exception in thread "main" java.util.concurrent.TimeoutException: deadline passed
            //due to rejection all messages from unknown people
            // should wrap with try-catch
        }


        // See the order (direct order of messages)
        // No method to take last message
        System.out.println("The Romeo's answer is " + romeoInbox.receive(Duration.create(1, TimeUnit.SECONDS)));
        System.out.println("The Romeo's answer is " + romeoInbox.receive(Duration.create(1, TimeUnit.SECONDS)));


        romeoInbox.watch(romeo);
        julietInbox.watch(juliet);


        // Kill 'em, Shakespeare!
        romeo.tell(PoisonPill.getInstance(), ActorRef.noSender());
        juliet.tell(PoisonPill.getInstance(), ActorRef.noSender());

        System.out.println(romeoInbox.receive(Duration.create(2, TimeUnit.SECONDS)));
        System.out.println(julietInbox.receive(Duration.create(2, TimeUnit.SECONDS)));

    }
}
