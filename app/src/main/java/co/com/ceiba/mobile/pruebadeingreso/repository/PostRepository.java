package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.CeibaDatabase;
import co.com.ceiba.mobile.pruebadeingreso.database.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.rest.Network;

/**
 * Repositorio de Datos Post
 */
public class PostRepository {

    private static final String URL_LIST_POST = Endpoints.URL_BASE + Endpoints.GET_POST_USER;

    private MutableLiveData<List<Post>> postsLiveData = new MutableLiveData<>();

    public PostRepository(int userId) {
        getList(userId);
    }

    /**
     * Obtiene la lista de post
     * @param userId
     */
    public void getList(int userId){

        Network.get(URL_LIST_POST + "userId=" + userId, new Network.ICallback() {
            @Override
            public void onFail(String code, String error) {
                postsLiveData.postValue(null);
            }

            @Override
            public void onSuccess(String response) {
                Type listType = new TypeToken<List<Post>>(){}.getType();
                List<Post> listPost = new Gson().fromJson(response, listType);
                postsLiveData.postValue(listPost);
            }
        });

    }

    public LiveData<List<Post>> getPostResponseLiveData() {
        return postsLiveData;
    }
}
