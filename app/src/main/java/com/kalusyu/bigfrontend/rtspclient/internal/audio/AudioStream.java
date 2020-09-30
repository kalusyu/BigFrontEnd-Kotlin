package com.kalusyu.bigfrontend.rtspclient.internal.audio;


import com.kalusyu.bigfrontend.rtspclient.internal.stream.RtpStream;

/**
 *
 */
public abstract class AudioStream extends RtpStream {
    private final static String tag = "AudioStream";

    @Override
    protected void recombinePacket(RtpStream.StreamPacks streamPacks) {

    }
}
