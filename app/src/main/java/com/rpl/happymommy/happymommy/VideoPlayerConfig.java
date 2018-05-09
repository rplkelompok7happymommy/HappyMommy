package com.rpl.happymommy.happymommy;

/**
 * Created by M. Satria Wibawa on 02/05/2018.
 */

public class VideoPlayerConfig {
    //Setting Buffer minimal dan maksimal video jalan
    public static final int MIN_BUFFER_DURATION = 25000;
    public static final int MAX_BUFFER_DURATION = 30000;
    //Minmal buffer ketika video dijalankan
    public static final int MIN_PLAYBACK_START_BUFFER = 10000;
    //Minimal buffer ketika video di resume
    public static final int MIN_PLAYBACK_RESUME_BUFFER = 10000;
    //Video Url
    public static final String VIDEO_URL = "https://ia601500.us.archive.org/29/items/Vid1HappyMommy/Hasil%20Compress3.mp4";
}
