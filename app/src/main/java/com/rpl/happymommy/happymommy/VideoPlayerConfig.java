package com.rpl.happymommy.happymommy;

/**
 * Created by M. Satria Wibawa on 02/05/2018.
 */

public class VideoPlayerConfig {
    //Minimum Video you want to buffer while Playing
    public static final int MIN_BUFFER_DURATION = 25000;
    //Max Video you want to buffer during PlayBack
    public static final int MAX_BUFFER_DURATION = 30000;
    //Min Video you want to buffer before start Playing it
    public static final int MIN_PLAYBACK_START_BUFFER = 10000;
    //Min video You want to buffer when user resumes video
    public static final int MIN_PLAYBACK_RESUME_BUFFER = 10000;
    //Video Url
    public static final String VIDEO_URL = "https://ia601500.us.archive.org/29/items/Vid1HappyMommy/Hasil%20Compress3.mp4";
}
