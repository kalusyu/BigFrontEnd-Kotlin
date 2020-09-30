package com.kalusyu.bigfrontend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.blankj.utilcode.util.LogUtils;

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/12 17:44
 **/
class YUVImageView extends SurfaceView {

    public static final String TAG = "YUVImageView";

    private SurfaceHolder.Callback callback = new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };

    public YUVImageView(Context context) {
        this(context, null);
    }

    public YUVImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YUVImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Rect srcRect;
    private Rect destRect;
    private BitmapFactory.Options options;
    private Canvas canvas;
    private Bitmap bitmap;

    private void initView() {
        surfaceHolder = getHolder();
        surfaceHolder.setFixedSize(640, 480);
        surfaceHolder.addCallback(callback);
        setZOrderOnTop(true);
        setZOrderMediaOverlay(true);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        srcRect = new Rect(0, 0, 640, 480);
        destRect = new Rect(0, 0, 640, 480);
        options = new BitmapFactory.Options();
    }

    public void drawBitmap(int id){
        if (surfaceHolder != null) {
            canvas = surfaceHolder.lockCanvas();
        }
        if (surfaceHolder != null && canvas != null) {
            try {
                canvas.drawColor(Color.WHITE);
                bitmap = BitmapFactory.decodeResource(getResources(),id);
                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, srcRect, destRect, paint);
                } else {
                    LogUtils.d(TAG, "create bitmap from yuvData array failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(TAG, "drawYUV error ", e);
            } finally {
                if (canvas != null && surfaceHolder != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }

    public void drawYUV(byte[] yuvData) {
        if (surfaceHolder != null) {
            canvas = surfaceHolder.lockCanvas();
        }
        if (surfaceHolder != null && canvas != null) {
            try {
                canvas.drawColor(Color.WHITE);
                bitmap = BitmapFactory.decodeByteArray(yuvData, 0, yuvData.length, options);
                if (bitmap != null) {
                    canvas.drawBitmap(bitmap, srcRect, destRect, paint);
                } else {
                    LogUtils.d(TAG, "create bitmap from yuvData array failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e(TAG, "drawYUV error ", e);
            } finally {
                if (canvas != null && surfaceHolder != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }
}
