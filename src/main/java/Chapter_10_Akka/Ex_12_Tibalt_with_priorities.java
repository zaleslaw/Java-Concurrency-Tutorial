package Chapter_10_Akka;

import Chapter_10_Akka.actors.*;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class Ex_12_Tibalt_with_priorities {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Theater");

        ActorRef romeo = system.actorOf(Props
                        .create(Postman.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Romeo");

        ActorRef juliet = system.actorOf(Props
                        .create(OutspokenActor.class), "Juliet");

        ActorRef tibalt = system.actorOf(Props
                        .create(Postman.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Tibalt");

        juliet.tell("Where's your nurse, Juliet?!", romeo);
        juliet.tell("Where's Romeo, I kill him?!", tibalt);

        romeo.tell(new Letter(4,"Have at thee, coward!"), tibalt);
        romeo.tell(new Letter(0,"What, drawn and talk of peace? I hate the word"), tibalt);
        romeo.tell(new Letter(2,"As I hate hell, all Montagues, and thee."), tibalt);
    }
}
