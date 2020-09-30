package com.kalusyu.bigfrontend.rtsp.rtspclinet.audio;


import com.kalusyu.bigfrontend.rtsp.rtspclinet.stream.RtpStream;

/**
 *
 */
public abstract class AudioStream extends RtpStream {
    private final static String tag = "AudioStream";

    protected void recombinePacket(StreamPacks streamPacks) {

    }
}
