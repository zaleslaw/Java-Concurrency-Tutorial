package Chapter_10_Akka;

import Chapter_10_Akka.actors.AnsweredActor;
import Chapter_10_Akka.actors.AngryForwarder;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static akka.pattern.PatternsCS.ask;

/**
 * OTHELLO handles DESDEMONA's requests, but he don't believe her words
 * It's time for IAGO
 * IAGO can handle it in time and hidden
 */
public class Ex_11_Slow_handling_with_Othello {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem system = ActorSystem.create("Theater");

        ActorRef othello = system.actorOf(Props.create(AngryForwarder.class), "Othello");
        ActorRef desdemona = system.actorOf(Props.create(AnsweredActor.class), "Desdemona");

        CompletableFuture<Object> othelloFuture = ask(othello, "Give me the Iago path!", 1000).toCompletableFuture();


        othello.tell(AngryForwarder.HANDLE_NEXT_MESSAGE, ActorRef.noSender());
        othello.tell("I hope my noble lord esteems me honest", desdemona);

        Thread.sleep(1000);


        // The Outspoken actor will intercept message instead AngryForwarder
        othello.tell(AngryForwarder.FORWARD_NEXT_MESSAGE, ActorRef.noSender());
        othello.tell("Alas, what ignorant sin have I committed?", desdemona);


        Timeout t = new Timeout(Duration.create(2, TimeUnit.SECONDS));
        // Get access to child actor through actorSelection method and resolving mechanism
        ActorRef iago = system.actorSelection((String) othelloFuture.get()).resolveOne(t).value().get().get();
        othello.tell(AngryForwarder.HANDLE_NEXT_MESSAGE, iago);
        othello.tell("Good my lord, pardon me:", iago);

        system.terminate();


    }
}
