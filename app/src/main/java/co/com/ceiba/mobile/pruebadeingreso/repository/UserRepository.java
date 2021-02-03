package co.com.ceiba.mobile.pruebadeingreso.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.database.CeibaDatabase;
import co.com.ceiba.mobile.pruebadeingreso.database.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import co.com.ceiba.mobile.pruebadeingreso.rest.Network;

public class UserRepository {

    private static final String URL_LIST_USER = Endpoints.URL_BASE + Endpoints.GET_USERS;

    //private MutableLiveData<List<User>> usersLiveData;
    private UserDao userDao;
    private MutableLiveData<List<User>> usersLiveData;

    public UserRepository(Application application) {
        CeibaDatabase database = CeibaDatabase.getInstance(application);
        userDao = database.userDao();
        //usersLiveData = userDao.getListUsers();
    }

    public void getList(){
        if(usersLiveData == null){
            Network.get(URL_LIST_USER, new Network.ICallback() {
                @Override
                public void onFail(String code, String error) {
                    usersLiveData.postValue(null);
                }

                @Override
                public void onSuccess(String response) {
                    Type listType = new TypeToken<List<User>>(){}.getType();
                    List<User> listUser = new Gson().fromJson(response, listType);
                    usersLiveData.postValue(listUser);
                    //usersLiveData;
                }
            });
        }
    }

    public void insert(User user){
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getUserResponseLiveData() {
        return usersLiveData;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

}
