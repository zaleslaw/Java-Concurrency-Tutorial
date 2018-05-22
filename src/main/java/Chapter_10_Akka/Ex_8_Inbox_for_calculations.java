package Chapter_10_Akka;

import Chapter_10_Akka.actors.ImprovedAccumulator;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 */
public class Ex_8_Inbox_for_calculations {
    public static void main(String[] args) throws TimeoutException {

        ActorSystem system = ActorSystem.create("MathLaboratory");

        ActorRef summator = system.actorOf(Props
                        .create(ImprovedAccumulator.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Summator");


        final Inbox inbox = Inbox.create(system);
        for (int i = 0; i < 1_000_000; i++) {
            inbox.send(summator, 1);
        }
        inbox.send(summator, ImprovedAccumulator.GET_THE_SUM);
        System.out.println("The result is " + inbox.receive(Duration.create(1, TimeUnit.SECONDS)));

    }
}
