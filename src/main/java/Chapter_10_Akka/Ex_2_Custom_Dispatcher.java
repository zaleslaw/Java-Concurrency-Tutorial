package Chapter_10_Akka;

import Chapter_10_Akka.actors.EmptyActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class Ex_2_Custom_Dispatcher {
    public static void main(String[] args) {
        Config config = ConfigFactory.load();

        ActorSystem system = ActorSystem.create("Theater", config);
        System.out.println(system.settings());

        ActorRef actor = system.actorOf(Props
                        .create(EmptyActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Makbet");

        // Print out actor unique reference
        System.out.println(actor.toString());
    }
}
