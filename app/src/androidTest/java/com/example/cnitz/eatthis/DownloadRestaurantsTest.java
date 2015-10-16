package com.example.cnitz.eatthis;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by sharrell on 10/16/15.
 */
public class DownloadRestaurantsTest extends InstrumentationTestCase {
    RestaurantDb rDB;
    Context context;

    public void setUp() {

        context = InstrumentationRegistry.getContext();
        rDB = new RestaurantDb(context, true);
        rDB.ResetDatabase();
    }

    public void testDownloadRestaurants() throws Throwable{

        final CountDownLatch signal = new CountDownLatch(1);
        final DownloadRestaurants downloadRestaurantsTask = new DownloadRestaurants(context);

        runTestOnUiThread(new Runnable() {
            public void run() {
                downloadRestaurantsTask.doInBackground();
            }
        });

        signal.await(30, TimeUnit.SECONDS);

        assertTrue(rDB.GetAllRestaurants().size() > 0);

    }


}
