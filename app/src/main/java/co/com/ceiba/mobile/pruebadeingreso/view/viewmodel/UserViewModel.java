package co.com.ceiba.mobile.pruebadeingreso.view.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.repository.UserRepository;
import lombok.NonNull;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> userLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userLiveData = userRepository.getUserResponseLiveData();
    }

    public LiveData<List<User>> getListUsers() {
        return userLiveData;
    }

}
