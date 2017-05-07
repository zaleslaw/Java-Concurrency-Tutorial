package Akka.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class EmptyActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }
}
