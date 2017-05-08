package Akka;

import Akka.actors.AnsweredActor;
import Akka.actors.OutspokenActor;
import Akka.actors.SpeakingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.util.Timeout;
import scala.concurrent.duration.Duration;

import static akka.pattern.PatternsCS.ask;
import static akka.pattern.PatternsCS.pipe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Hamlet and Ghost join their power and kill Claudius, the King
 */
public class Ex_10_Patterns_With_Hamlet {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ActorSystem system = ActorSystem.create("Theater");
        // 1 step: Simple actor without reply
        // akka.pattern.AskTimeoutException
        // ActorRef gamlet = system.actorOf(Props.create(SpeakingActor.class), "Gamlet");


        // 2 step:
        ActorRef hamlet = system.actorOf(Props.create(AnsweredActor.class), "Gamlet");


        CompletableFuture<Object> hamletFuture = ask(hamlet, "To Be or Not to Be", 1000).toCompletableFuture();

        System.out.println(hamletFuture.get());


        // 3 step: Combine GHOST and GAMLET to one pipeline
        ActorRef ghost = system.actorOf(Props.create(AnsweredActor.class), "Ghost");
        Timeout slowGhost = new Timeout(Duration.create(2, TimeUnit.SECONDS));
        CompletableFuture<Object> ghostFuture = ask(hamlet, "So art thou to revenge, when thou shalt hear", slowGhost)
                .toCompletableFuture();

        CompletableFuture<PoisonPill> claudiusFuture =
                CompletableFuture.allOf(hamletFuture, ghostFuture)
                        .thenApply(v -> {
                            hamletFuture.join();
                            ghostFuture.join();
                            return PoisonPill.getInstance();
                        });


        ActorRef claudius = system.actorOf(Props.create(OutspokenActor.class), "Claudius");

        pipe(claudiusFuture, system.dispatcher()).to(claudius);
        Thread.sleep(3000);
        System.out.println("KING CLAUDIUS dies: " + claudius.isTerminated());


    }
}
