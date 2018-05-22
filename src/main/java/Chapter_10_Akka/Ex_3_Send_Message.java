package Chapter_10_Akka;


import Chapter_10_Akka.actors.SpeakingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Alexey_Zinovyev on 07-May-17.
 */
public class Ex_3_Send_Message {
    public static void main(String[] args) {
        Config config = ConfigFactory.load();

        ActorSystem system = ActorSystem.create("Theater", config);

        ActorRef actor = system.actorOf(Props
                        .create(SpeakingActor.class)
                        .withDispatcher("akka.actor.custom-dispatcher"),
                "Makbet");

        actor.tell(1, ActorRef.noSender());

    }
}

