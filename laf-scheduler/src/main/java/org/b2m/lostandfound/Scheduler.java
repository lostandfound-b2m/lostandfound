package org.b2m.lostandfound;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    public void update() throws IOException {
        Retriever retrieverGd = new GdRetriever();
        Retriever retrieverKrk = new KrkRetriever();
        Service service = new Service();
        RegularUpdater updaterGd = new RegularUpdater(retrieverGd, service);
        RegularUpdater updaterKrk = new RegularUpdater(retrieverKrk, service);
        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("Scheduler starts now");
                try {
                    updaterGd.doScheduledUpdate();
                    updaterKrk.doScheduledUpdate();
                }
                catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        }, 5, TimeUnit.MINUTES);
    }
}
