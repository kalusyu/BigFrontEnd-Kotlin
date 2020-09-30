package com.kalusyu.bigfrontend.mediacodec

import android.media.MediaCodec
import android.media.MediaCodecList
import android.media.MediaExtractor
import android.media.MediaFormat
import android.os.Bundle
import android.util.Log
import android.view.Surface
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import com.kalusyu.bigfrontend.R
import kotlinx.android.synthetic.main.mediacodec_layout.*
import java.io.IOException
import java.lang.Thread.sleep


/**
 * desc:MediaCodec的使用示例
 *
 * @author biaowen.yu
 * @date 2020/7/15 19:26
 *
 **/
class MediaCodecActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MediaCodecActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mediacodec_layout)
        val extractor = MediaExtractor()
        extractor.setDataSource("/sdcard/DCIM/Camera/VID_20190911_112108.mp4")
        dumpFormat(extractor)
        displayDecoders()


        media_codec_surface.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                val selTrackFmt = chooseVideoTrack(extractor)
                val codec = createCodec(selTrackFmt!!, media_codec_surface.holder.surface)
                // 用于对准视频的时间戳
                val startMs = System.currentTimeMillis()
                codec?.run {
                    setCallback(object : MediaCodec.Callback() {


                        override fun onOutputBufferAvailable(
                            codec: MediaCodec,
                            index: Int,
                            info: MediaCodec.BufferInfo
                        ) {
                            sleepRender(info, startMs)
                            codec.releaseOutputBuffer(index, true);
                        }

                        override fun onInputBufferAvailable(codec: MediaCodec, index: Int) {
                            val buffer = codec.getInputBuffer(index)
                            val sampleSize = extractor.readSampleData(buffer!!, 0)
                            if (sampleSize < 0) {
                                codec.queueInputBuffer(
                                    index,
                                    0,
                                    0,
                                    0,
                                    MediaCodec.BUFFER_FLAG_END_OF_STREAM
                                )
                            } else {
                                val sampleTime = extractor.sampleTime
                                codec.queueInputBuffer(index, 0, sampleSize, sampleTime, 0)
                                extractor.advance()
                            }
                        }

                        override fun onOutputFormatChanged(codec: MediaCodec, format: MediaFormat) {
                        }

                        override fun onError(codec: MediaCodec, e: MediaCodec.CodecException) {
                        }
                    })

                    start()
                }
            }
        })
    }

    /**
     *  数据的时间戳对齐
     */
    private fun sleepRender(audioBufferInfo: MediaCodec.BufferInfo, startMs: Long) {
        // 这里的时间是 毫秒  presentationTimeUs 的时间是累加的 以微秒进行一帧一帧的累加
        val timeDifference =
            audioBufferInfo.presentationTimeUs / 1000 - (System.currentTimeMillis() - startMs)
        if (timeDifference > 0) {
            try {
                sleep(timeDifference)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun dumpFormat(extractor: MediaExtractor) {
        val count = extractor.trackCount
        Log.i(TAG, "playVideo: track count: $count")
        for (i in 0 until count) {
            val format = extractor.getTrackFormat(i)
            Log.i(TAG, "playVideo: track " + i + ":" + getTrackInfo(format))
        }
    }

    private fun getTrackInfo(format: MediaFormat): String? {
        var info = format.getString(MediaFormat.KEY_MIME)
        if (info.startsWith("audio/")) {
            info += (" samplerate: " + format.getInteger(MediaFormat.KEY_SAMPLE_RATE)
                    + ", channel count:" + format.getInteger(MediaFormat.KEY_CHANNEL_COUNT))
        } else if (info.startsWith("video/")) {
            info += " size:" + format.getInteger(MediaFormat.KEY_WIDTH) + "x" + format.getInteger(
                MediaFormat.KEY_HEIGHT
            )
        }
        return info
    }

    private fun displayDecoders() {
        val list =
            MediaCodecList(MediaCodecList.REGULAR_CODECS) //REGULAR_CODECS参考api说明
        val codecs = list.codecInfos
        for (codec in codecs) {
            if (codec.isEncoder) continue
            Log.i(TAG, "displayDecoders: " + codec.name)
        }
    }

    private fun chooseVideoTrack(extractor: MediaExtractor): MediaFormat? {
        val count = extractor.trackCount
        for (i in 0 until count) {
            val format = extractor.getTrackFormat(i)
            if (format.getString(MediaFormat.KEY_MIME).startsWith("video/")) {
                extractor.selectTrack(i) //选择轨道
                return format
            }
        }
        return null
    }

    @Throws(IOException::class)
    private fun createCodec(format: MediaFormat, surface: Surface): MediaCodec? {
        val codecList = MediaCodecList(MediaCodecList.REGULAR_CODECS)
        val codec = MediaCodec.createByCodecName(codecList.findDecoderForFormat(format))
        codec.configure(format, surface, null, 0)
        return codec
    }

}