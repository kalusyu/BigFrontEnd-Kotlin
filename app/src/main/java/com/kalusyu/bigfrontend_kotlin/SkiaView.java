package com.kalusyu.bigfrontend_kotlin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SkiaView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder = null;//******Version2******//
    private static final String TAG = "skiademo";

    private int mSampleId = 0;
    // final Rect rect = new Rect(0, 0, 352, 288);
    final int width = 352, height = 288;

    int FrameNum = 0;


    class Pixel {
        byte Y, U, V;
    }

    ;

    private int[] YUV2RGB(byte[] YUV) {
        Byte R, G, B;
        int y, x, i;
        int[] argb = new int[width * height];
        byte[] Y = new byte[width * height];
        byte[] U = new byte[width * height];
        byte[] V = new byte[width * height];
        for (i = 0; i < width * height; i++) {
            Y[i] = YUV[i];
            if (i == 0)
                Log.d(TAG, "Y=" + Y[i] + " YUV[i]=" + YUV[i]);
        }
        i = 0;
        byte container;
        for (y = 0; y < height / 2; y++)
            for (x = 0; x < width / 2; x++) {
                container = YUV[i + width * height];
                U[y * 2 * width + x * 2] = container;
                U[y * 2 * width + x * 2 + 1] = container;
                U[(y * 2 + 1) * width + x * 2] = container;
                U[(y * 2 + 1) * width + x * 2 + 1] = container;
                i++;

            }

        i = 0;
        for (y = 0; y < height / 2; y++)
            for (x = 0; x < width / 2; x++) {
                container = YUV[i + width * height + width * height / 4];
                V[y * 2 * width + x * 2] = container;
                V[y * 2 * width + x * 2 + 1] = container;
                V[(y * 2 + 1) * width + x * 2] = container;
                V[(y * 2 + 1) * width + x * 2 + 1] = container;

            }
        for (i = 0; i < width * height; i++) {
            R = (byte) (Y[i] + 128 + 1.4075 * (V[i] + 128 - 128));
            G = (byte) (Y[i] + 128 - 0.3455 * (U[i] + 128 - 128) - 0.7169 * (V[i] + 128 - 128));
            B = (byte) (Y[i] + 128 + 1.7790 * (U[i] - 128 + 128));
            argb[i] = (0xff000000 + (B << 16) + (G << 8) + R);
            if (i == 0) {
                Log.d(TAG, "Y=" + Y[i] + " U=" + U[i] + " V= " + V[i]);
                Log.d(TAG, "R=" + R + " G=" + G + "B= " + B + " argb=" + argb[0]);
            }

        }

        //Bitmap bmp=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);


        return argb;


    }

    private void readYUV(String src, byte[] YUV, int frame) {
        int i;
        try {
            FileInputStream inFile = new FileInputStream(src);
            int filelength = inFile.available();
            Log.d(TAG, "frame=" + frame + " start=" + width * height * 1.5 * frame + " Length=" + width * height * 1.5 + "   Filelength=" + filelength);
            //inFile.read(YUV, (int)(width*height*1.5*(frame-1)),(int)(width*height*1.5));      
            for (i = 0; i < width * height * 1.5; i++)
                YUV[i] = (byte) inFile.read();

            Log.d(TAG, " YUV[i]=" + YUV[0]);
            inFile.close();
        } catch (FileNotFoundException fe) {
            System.out.println("There is no such file!");

        } catch (IOException ioe) {
            System.out.println("IO exception!");

        }

    }

    public SkiaView(Context context)//******Version2******//
    {
        super(context);
        // TODO Auto-generated constructor stub
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)//******Version2******//
    {
        // TODO Auto-generated method stub
        new Thread(this).start();
    }

    private Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    private void render(Canvas canvas, int i) {


        canvas.drawColor(Color.BLACK);
        byte[] YUV = new byte[(int) (width * height * 1.5)];
        int[] rgb = new int[width * height];
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        readYUV("/mnt/sdcard/out_352x288_P420_final.yuv", YUV, i);
        rgb = YUV2RGB(YUV);

        bmp.setPixels(rgb, 0, width, 0, 0, width, height);
        Log.d(TAG, "pixel=" + bmp.getPixel(0, 0));
        canvas.drawBitmap(bmp, 0, 0, null);

    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            Canvas canvas = holder.lockCanvas();
            render(canvas, i);
            i++;
            if (i >= 299)
                i = 0;
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }


}