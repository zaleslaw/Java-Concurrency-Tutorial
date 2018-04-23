package Chapter_10_Akka;


import Chapter_10_Akka.actors.EmptyActor;
import akka.actor.*;

/**
 * An ActorSystem is a heavyweight structure that will allocate 1…N Threads, so create one per logical application
 */
public class Ex_1_FirstActor {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Theater");
        ActorRef actor = system.actorOf(Props.create(EmptyActor.class), "Makbet");

        // Print out actor unique reference
        System.out.println(actor.toString());
        System.out.println(actor.isTerminated());

    }
}


