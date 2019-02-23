package consulting.dfort.dforttemplateads;

import android.app.ActionBar;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import consulting.dfort.dforttemplateads.base.BaseActivity;
import consulting.dfort.dforttemplateads.widgets.MyVideoView;

/**
 * Created by pc on 23/02/2019 11:57.
 * DFortTemplateAds
 */
public class HomeVideoActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {
    private ImageSwitcher imageSwitcher1, imageSwitcher2, imageSwitcher3, imageSwitcher4;
    private MediaPlayer mMediaPlayer;
    private int[] galleryh = {R.drawable.h1, R.drawable.h2};
    private int[] galleryb = {R.drawable.b1, R.drawable.b2};
    private int[] galleryc = {R.drawable.car1, R.drawable.car2};
    private int[] gallerys = {R.drawable.s1, R.drawable.s2};
    private int positionh = 0;
    private int positionb = 0;
    private int positions = 0;
    private int positionc = 0;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private MyVideoView videoView;
    int stopPosition = 0;
    private MediaController mediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_with_video_layout);
        init();
        setImageSwitcher(imageSwitcher1);
        setImageSwitcher(imageSwitcher2);
        setImageSwitcher(imageSwitcher3);
        start();

    }


    /**/
    private void init() {
        imageSwitcher1 = findViewById(R.id.imageSwitcher1);
        imageSwitcher2 = findViewById(R.id.imageSwitcher2);
        imageSwitcher3 = findViewById(R.id.imageSwitcher3);
        mSurfaceView = findViewById(R.id.imageSwitcher4);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    private void setImageSwitcher(ImageSwitcher imageSwitcher) {
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                switcherImageView.setImageResource(R.drawable.dflogo);
                //switcherImageView.setMaxHeight(100);
                return switcherImageView;
            }
        });

        // Set animations http://danielme.com/2013/08/18/diseno-android-transiciones-entre-activities/
        Animation fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);

    }


    public void start() {
        /*Switch 1*/
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                    runOnUiThread(new Runnable() {
                        public void run() {
                            imageSwitcher1.setImageResource(galleryh[positionh]);
                            positionh++;
                            if (positionh == 2) {
                                positionh = 0;
                            }
                        }
                    });
                }
            }, 0, 5000);
        }
        /*Switch 2*/
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                    runOnUiThread(new Runnable() {
                        public void run() {
                            imageSwitcher2.setImageResource(galleryb[positionb]);
                            positionb++;
                            if (positionb == 2) {
                                positionb = 0;
                            }
                        }
                    });
                }
            }, 0, 7000);
        }
        /*Switch 3*/
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                    runOnUiThread(new Runnable() {
                        public void run() {
                            imageSwitcher3.setImageResource(galleryc[positionc]);
                            positionc++;
                            if (positionc == 2) {
                                positionc = 0;
                            }
                        }
                    });
                }
            }, 0, 5000);
        }


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setDisplay(mSurfaceHolder);


        try {
            AssetFileDescriptor afd = getAssets().openFd("output.mp4");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                mMediaPlayer.setDataSource(afd);
            } else {
            }
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }
}
