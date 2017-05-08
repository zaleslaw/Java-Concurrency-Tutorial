package Akka.actors;

/**
 * Created by Alexey_Zinovyev on 08-May-17.
 */
public class Letter {
    private int priority;
    private String body;

    public Letter(int priority, String body) {
        this.priority = priority;
        this.body = body;
    }

    public int getPriority() {
        return priority;
    }

    public String getBody() {
        return body;
    }
}
