package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserAdater;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdater userAdater;

    private RecyclerView recyclerViewSearchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAdater = new UserAdater();

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getListUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                Toast.makeText(MainActivity.this, "Change User", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}