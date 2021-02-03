package co.com.ceiba.mobile.pruebadeingreso.view.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.repository.PostRepository;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import lombok.NonNull;

public class PostViewModel extends AndroidViewModel {

    private PostRepository postRepository;
    private LiveData<List<Post>> postLiveData;

    public PostViewModel(@NonNull Application application, int userId) {
        super(application);
        postRepository = new PostRepository(userId);
        postLiveData = postRepository.getPostResponseLiveData();
    }



    public LiveData<List<Post>> getListPost() {
        if(postLiveData == null)
            postLiveData = postRepository.getPostResponseLiveData();
        return postLiveData;
    }
}
