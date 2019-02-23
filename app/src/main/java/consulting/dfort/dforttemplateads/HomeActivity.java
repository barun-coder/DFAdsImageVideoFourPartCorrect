package consulting.dfort.dforttemplateads;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

import consulting.dfort.dforttemplateads.base.BaseActivity;

/**
 * Created by pc on 23/02/2019 11:57.
 * DFortTemplateAds
 */
public class HomeActivity extends BaseActivity {
    private ImageSwitcher imageSwitcher1, imageSwitcher2, imageSwitcher3, imageSwitcher4;

    private int[] galleryh = {R.drawable.h1, R.drawable.h2};
    private int[] galleryb = {R.drawable.b1, R.drawable.b2};
    private int[] galleryc = {R.drawable.car1, R.drawable.car2};
    private int[] gallerys = {R.drawable.s1, R.drawable.s2};
    private int positionh = 0;
    private int positionb = 0;
    private int positions = 0;
    private int positionc = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        init();
        setImageSwitcher(imageSwitcher1);
        setImageSwitcher(imageSwitcher2);
        setImageSwitcher(imageSwitcher3);
        setImageSwitcher(imageSwitcher4);
        start();

    }

    private void setImageSwitcher(ImageSwitcher imageSwitcher) {
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                ImageView switcherImageView = new ImageView(getApplicationContext());
                switcherImageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                ));
                switcherImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
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

    private void init() {
        imageSwitcher1 = findViewById(R.id.imageSwitcher1);
        imageSwitcher2 = findViewById(R.id.imageSwitcher2);
        imageSwitcher3 = findViewById(R.id.imageSwitcher3);
        imageSwitcher4 = findViewById(R.id.imageSwitcher4);
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
        /*Switch 4*/
        {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    // avoid exception: "Only the original thread that created a view hierarchy can touch its views"
                    runOnUiThread(new Runnable() {
                        public void run() {
                            imageSwitcher4.setImageResource(gallerys[positions]);
                            positions++;
                            if (positions == 2) {
                                positions = 0;
                            }
                        }
                    });
                }
            }, 0, 7000);
        }

    }

}
