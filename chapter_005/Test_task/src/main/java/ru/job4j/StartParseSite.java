package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**.
 * Task 8.5.1.
 * Search offers the job
 *
 * @author Anton Vasilyuk
 * @version 1.0.
 */
public class StartParseSite {

    /**.
     * Create logger
     */
    private final static Logger log = LoggerFactory.getLogger(StartParseSite.class);

    public static void main(String[] args) {

        final long period = 86400000L;

        ConnectionWeb conWeb = new ConnectionWeb();
        ConnectDB db = new ConnectDB();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Start parsing...");
                try {
                    conWeb.getDataWebSite();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
                log.info("End parsing");
                db.printDB();
            }
        }, period);
    }
}