package com.example.speedtestapp;

public class SpeedTestThread implements Runnable{
    @Override
    public void run() {
        new DownloadSpeedTestTask(10000).execute();
    }
}
