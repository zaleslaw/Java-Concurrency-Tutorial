package Chapter_10_Akka;

import Chapter_10_Akka.actors.Accumulator;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class Ex_4_Accumulator {


    public static void main(String[] args) {
        Config config = ConfigFactory.load();

        ActorSystem system = ActorSystem.create("Theater", config);

        ActorRef actor = system.actorOf(Props
                        .create(Accumulator.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Summator");

        actor.tell(1, ActorRef.noSender());
        actor.tell(10,  ActorRef.noSender());
        actor.tell(-3,  ActorRef.noSender());
        actor.tell(Accumulator.GET_THE_SUM, null);


    }
}

