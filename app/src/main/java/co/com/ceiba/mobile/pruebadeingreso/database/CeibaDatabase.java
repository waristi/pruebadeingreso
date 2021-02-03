package co.com.ceiba.mobile.pruebadeingreso.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import co.com.ceiba.mobile.pruebadeingreso.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class CeibaDatabase extends RoomDatabase {

    private static CeibaDatabase instance;

    public abstract UserDao userDao();

    public static synchronized CeibaDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CeibaDatabase.class, "ceiba_database")
                    .fallbackToDestructiveMigration()
                    //.addCallback(roomCallback)
                    .build();
        }

        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateDbAsyncTask(CeibaDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //userDao.insert(new User());
            return null;
        }
    }

}
