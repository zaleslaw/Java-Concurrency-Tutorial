package Chapter_2_Basic_Concurrency.Part_5_Livelock;

public class Rabochii_i_Lopata {

    public static void main(String[] args) {
        final Rabochiy rabochiy1 = new Rabochiy("Rabochiy 1 ", true);
        final Rabochiy rabochiy2 = new Rabochiy("Rabochiy 2", true);

        final Lopata s = new Lopata(rabochiy1);

        new Thread(() -> {
            rabochiy1.work(s, rabochiy2);
        }).start();

        new Thread(() -> {
            rabochiy2.work(s, rabochiy1);
        }).start();
    }

    private static class Lopata {
        private Rabochiy owner;

        public Lopata(Rabochiy d) {
            owner = d;
        }

        public Rabochiy getOwner() {
            return owner;
        }

        public synchronized void setOwner(Rabochiy d) {
            owner = d;
        }
    }

    private static class Rabochiy {
        private String name;
        private boolean active;

        public Rabochiy(String name, boolean active) {
            this.name = name;
            this.active = active;
        }

        public String getName() {
            return name;
        }

        public boolean isActive() {
            return active;
        }

        public synchronized void work(Lopata commonResource, Rabochiy otherRabochiy) {
            while (active) {
                // wait for the resource to become available.
                if (commonResource.getOwner() != this) {
                    try {
                        wait(10);
                    } catch (InterruptedException e) {
                        //ignore
                    }
                    continue;
                }

                // If other worker is also active let it do it's work first
                if (otherRabochiy.isActive()) {
                    System.out.println(getName() +
                            " : handover the resource to the worker " +
                            otherRabochiy.getName());
                    commonResource.setOwner(otherRabochiy);
                    continue;
                }

                //now use the commonResource
                System.out.println(getName() + ": working on the common resource");
                active = false;
                commonResource.setOwner(otherRabochiy);
            }
        }
    }
}