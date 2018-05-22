package Chapter_10_Akka;

import Chapter_10_Akka.actors.OutspokenActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class Ex_5_Naive_Actor_to_Actor_Communication {
    public static void main(String[] args) {
        Config config = ConfigFactory.load();

        ActorSystem system = ActorSystem.create("Theater", config);

        ActorRef romeo = system.actorOf(Props
                        .create(OutspokenActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Romeo");

        ActorRef juliet = system.actorOf(Props
                        .create(OutspokenActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Juliet");


        romeo.tell("Hi, Romeo!", juliet);
        juliet.tell("Where's your nurse, Juliet?!", romeo);

    }
}
