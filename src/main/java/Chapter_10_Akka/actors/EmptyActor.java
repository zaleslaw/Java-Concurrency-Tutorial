package Chapter_10_Akka.actors;

import akka.actor.AbstractActor;

public class EmptyActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }
}
