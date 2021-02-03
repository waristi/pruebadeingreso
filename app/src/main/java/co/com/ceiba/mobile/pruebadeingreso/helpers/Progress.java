package co.com.ceiba.mobile.pruebadeingreso.helpers;

import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Esta clase me permite crear un progress
 * @author Bernardo Alexander Zuluaga Aristizabal
 */
public class Progress {

    public static ProgressBar progress;
    public static View content;
    public static boolean gone;

    /**
     * Metodo Inicial
     * @param mProgress
     * @param mContent
     * @param mGone
     */
    public static void init(ProgressBar mProgress, View mContent, boolean mGone) {
        progress = mProgress;
        content = mContent;
        gone = mGone;
    }

    public static void show(final ICallback callback) {
        if (gone) {
            content.setVisibility(View.GONE);
            progress.setVisibility(View.VISIBLE);
        } else {
            content.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.VISIBLE);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.run();
            }
        }, 500);
    }

    public static void hidden() {
        if (gone) {
            content.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        } else {
            content.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
        }
    }

    public interface ICallback {
        void run();
    }
}

