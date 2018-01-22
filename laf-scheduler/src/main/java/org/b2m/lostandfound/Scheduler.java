package org.b2m.lostandfound;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Klasa uruchamiająca co określony czas RegularUpdater dla Retrievera
 * dla każdego biura.
 * @see RegularUpdater
 * @see Retriever
 */
public class Scheduler {

    public void update() {
        Retriever retrieverGd = new GdRetriever();
        Retriever retrieverKrk = new KrkRetriever();
        Retriever retrieverWaw = new WawRetriever();
        Service service = new Service();
        RegularUpdater updaterGd = new RegularUpdater(retrieverGd, service);
        RegularUpdater updaterKrk = new RegularUpdater(retrieverKrk, service);
        RegularUpdater updaterWaw = new RegularUpdater(retrieverWaw, service);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Scheduler starts now");
                    updaterGd.doScheduledUpdate();
                    updaterKrk.doScheduledUpdate();
                    updaterWaw.doScheduledUpdate();
            }
        }, 0,1, TimeUnit.MINUTES);
    }
}
