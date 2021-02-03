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

/**
 * Repositorio de datos de Usuario
 */
public class UserRepository {

    private static final String URL_LIST_USER = Endpoints.URL_BASE + Endpoints.GET_USERS;

    private UserDao userDao;
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    public UserRepository(Application application) {
        CeibaDatabase database = CeibaDatabase.getInstance(application);
        userDao = database.userDao();
        getList();
    }

    /**
     * Obtiene la lista de usuarios de la bd, y si esta vacia obtiene del servicio
     */
    public void getList(){
        new GetUserAsyncTask(userDao, new GetUserAsyncTask.CallbackDao() {
            @Override
            public void response(List<User> users) {
                if(users.isEmpty()){
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

                            // PERSISTE LA DATA
                            new InsertUserAsyncTask(userDao).execute(listUser);
                        }
                    });
                }else{
                    usersLiveData.postValue(users);
                }
            }
        } ).execute();
    }

    public LiveData<List<User>> getUserResponseLiveData() {
        return usersLiveData;
    }

    /**
     * Hilo para obtener los usuarios
     */
    public static class GetUserAsyncTask extends AsyncTask<Void, Void, List<User>> {

        private UserDao userDao;
        private CallbackDao callback;

        public GetUserAsyncTask(UserDao userDao, CallbackDao callback) {
            this.userDao = userDao;
            this.callback = callback;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return  userDao.getListUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            callback.response(users);
        }

        public interface CallbackDao {
            void response(List<User> users);
        }
    }

    /**
     * Hilo para insertar los usuarios
     */
    private static class InsertUserAsyncTask extends AsyncTask<List<User>, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(List<User>... users) {
            List<User> listUser = users[0];

            for(User user: listUser) {
                userDao.insert(user);
            }

            return null;
        }
    }

}
