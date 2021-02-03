package co.com.ceiba.mobile.pruebadeingreso.rest;

import android.os.Handler;
import android.os.Looper;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Esta clase me permite tener acceso a los metodos REST
 * GET, POST, DELETE, UPDATE
 * @author Bernardo Alexander Zuluaga Arisatizabal
 */
public class Network {

    public static final MediaType CONTENT_TYPE =
            MediaType.parse("application/json; charset=utf-8");

    /**
     * METODO GET API
     *
     * @param url
     * @param callback
     */
    public static void get(String url, ICallback callback) {

        Request request = new Request.Builder()
                .url(url)
                .build();
        excecute(request, callback);
    }


    /**
     * METODO POST API
     *
     * @param url
     * @param json
     * @param callback
     */
    public static void post(String url, String json, final ICallback callback) {

        RequestBody body = RequestBody.create(CONTENT_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        excecute(request, callback);
    }


    /**
     * MEGOTO PUT API
     * @param url
     * @param json
     * @param callback
     */
    public static void put(String url, String json, ICallback callback) {

        RequestBody body = RequestBody.create(CONTENT_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        excecute(request, callback);
    }


    /**
     * MEGOTO PUT API
     * @param url
     * @param json
     * @param callback
     */
    public static void put(String url, String json, String token, ICallback callback) {

        RequestBody body = RequestBody.create(CONTENT_TYPE, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .header("Authorization", "Bearer " + token)
                .build();

        excecute(request, callback);

    }

    /**
     * EJECUTA METODO API
     * @param request
     * @param callback
     */
    public static void excecute(Request request, final ICallback callback) {

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final String error = e.getMessage();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFail("SYSTEM", error);
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    final String resp = response.body().string();

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess(resp);
                        }
                    });

                } else {
                    String remoteResponse = response.body().string();
                    try {
                        JSONObject json = new JSONObject(remoteResponse);
                        final String code = json.getString("code");
                        final String error = json.getString("error");

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFail(code, error);
                            }
                        });
                    } catch (final JSONException e) {
                        //callback.onFail("SYSTEM", e.getMessage());
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFail("SYSTEM", e.getMessage());
                            }
                        });
                    }

                }
            }
        });
    }

    /**
     * INTERFACE CALLBACK
     */
    public interface ICallback {
        void onFail(String code, String error);

        void onSuccess(String response);
    }
}



