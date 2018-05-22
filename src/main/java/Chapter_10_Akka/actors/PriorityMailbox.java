package Chapter_10_Akka.actors;

import akka.actor.ActorSystem;
import akka.dispatch.Envelope;
import akka.dispatch.PriorityGenerator;
import akka.dispatch.UnboundedStablePriorityMailbox;
import com.typesafe.config.Config;

import java.util.Comparator;

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
