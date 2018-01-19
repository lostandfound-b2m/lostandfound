package org.b2m.lostandfound;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    public void update() {
        Retriever retrieverGd = new GdRetriever();
        Retriever retrieverKrk = new KrkRetriever();
        Retriever retrieverWaw = new WawRetriever();
        Service service = new Service();
        RegularUpdater updaterGd = new RegularUpdater(retrieverGd, service);
        RegularUpdater updaterKrk = new RegularUpdater(retrieverKrk, service);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Scheduler starts now");
                try {
                    service.addItems(service.simpleGetList(retrieverWaw));
                    service.addItems(service.simpleGetList(retrieverGd));
                    service.addItems(service.simpleGetList(retrieverKrk));

                }
                catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        }, 0,2, TimeUnit.MINUTES);
    }
}
