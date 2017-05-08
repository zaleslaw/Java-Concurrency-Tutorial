package Akka.actors;

import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.dispatch.BoundedStablePriorityMailbox;
import akka.dispatch.Envelope;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedStablePriorityMailbox;
import com.typesafe.config.Config;
import scala.concurrent.duration.Duration;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexey_Zinovyev on 08-May-17.
 */
public class PriorityMailbox extends UnboundedStablePriorityMailbox {
    public PriorityMailbox(ActorSystem.Settings settings, Config config) {
        super(getPriorityGenerator());
    }


    @Override
    public int initialCapacity() {
        return 10;
    }

    @Override
    public Comparator<Envelope> cmp() {
        return getPriorityGenerator();
    }

    private static PriorityGenerator getPriorityGenerator() {
        return new PriorityGenerator() {
            @Override
            public int gen(Object letter) {
                if(letter!=null && letter instanceof Letter){
                    return ((Letter)letter).getPriority();
                }
                return 10;
            }
        };
    }
}
