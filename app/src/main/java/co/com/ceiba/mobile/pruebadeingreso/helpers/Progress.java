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

    /**
     * Recibe el cotexto el titulo del progress y el mensaje
     * @param context
     * @param title
     * @param message
     */
    public static void show(Context context, String title, String message){
        progressDialog = new ProgressDialog(context);

        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /**
     * Cierra el progress
     */
    public static void dismiss() {
        if(progressDialog != null)
            progressDialog.dismiss();
    }
}

