package co.com.ceiba.mobile.pruebadeingreso.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Esta clase me permite crear un progress
 * @author Bernardo Alexander Zuluaga Aristizabal
 */
public class Progress {


    public static ProgressDialog progressDialog;

    public static void show(Context context, String title, String message){
        progressDialog = new ProgressDialog(context);

        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public static void dismiss() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}

