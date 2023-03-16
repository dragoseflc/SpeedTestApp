package com.example.speedtestapp;

import android.os.AsyncTask;
import android.util.Log;
import java.math.BigDecimal;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.IRepeatListener;

public class DownloadSpeedTestTask extends AsyncTask<Void, Void, String> {

    public static BigDecimal dlSpeedCurr;

    public static BigDecimal dlSpeedAvg;

    int millis;

    public DownloadSpeedTestTask(int millis) {
        this.millis = millis;
    }

    @Override
    protected String doInBackground(Void... params) {

        SpeedTestSocket speedTestSocketDownload = new SpeedTestSocket();

        speedTestSocketDownload.startDownloadRepeat("http://ipv4.ikoula.testdebit.info/100M.iso",
                millis, 1000, new
                        IRepeatListener() {
                            @Override
                            public void onCompletion(final SpeedTestReport report) {
                                // called when repeat task is finished
                                //Log.v("speedtest", "[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                                dlSpeedAvg = report.getTransferRateBit();
                                Log.v("speedtest", "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
                            }

                            @Override
                            public void onReport(final SpeedTestReport report) {
                                // called when a download report is dispatched
                                //Log.v("speedtest DOWNLOAD", "[REPORT] rate in octet/s : " + report.getTransferRateOctet());
                                dlSpeedCurr = report.getTransferRateBit();
                                Log.v("speedtest DOWNLOAD", "[REPORT] rate in bit/s   : " + report.getTransferRateBit());
                            }
                        });
        return null;
    }
}
