package Chapter_10_Akka;

import Chapter_10_Akka.actors.FilteredActor;
import Chapter_10_Akka.actors.OutspokenActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 *
 */
public class Ex_6_Reply_from_Actor_to_Actor {
    public static void main(String[] args) {
        Config config = ConfigFactory.load();

        ActorSystem system = ActorSystem.create("Theater", config);

        ActorRef romeo = system.actorOf(Props
                        .create(OutspokenActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Romeo");

        ActorRef juliet = system.actorOf(Props
                        .create(FilteredActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Juliet");

        ActorRef tibalt = system.actorOf(Props
                        .create(OutspokenActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Tibalt");

        juliet.tell("Where's your nurse, Juliet?!", romeo);
        juliet.tell("Where's Romeo, I kill him?!", tibalt);

    }
}
