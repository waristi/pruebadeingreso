package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.PostAdapter;
import co.com.ceiba.mobile.pruebadeingreso.helpers.Progress;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.viewmodel.PostViewModel;

/**
 * ACTIVIDAD POST
 * Contiene la lista de post y detalle del usuario
 */
public class PostActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostAdapter postAdapter;

    private RecyclerView recyclerViewPostsResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initUser();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Setea los datos en el card
     */
    public void initUser() {
        User user = (User) getIntent().getSerializableExtra("USER");

        if(user != null){
            setTitle(user.getName());
            TextView tvName = findViewById(R.id.name);
            TextView tvPhone = findViewById(R.id.phone);
            TextView tvEmail = findViewById(R.id.email);

            tvName.setText(user.getName());
            tvPhone.setText(user.getPhone());
            tvEmail.setText(user.getEmail());

            initList();
            initViewModel(user.getId());
        }
    }

    /**
     * Inicializa la lista de Post
     */
    public void initList() {
        recyclerViewPostsResults = findViewById(R.id.recyclerViewPostsResults);
        recyclerViewPostsResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPostsResults.setHasFixedSize(true);

        postAdapter = new PostAdapter();
        recyclerViewPostsResults.setAdapter(postAdapter);
    }

    /**
     * Inicializa el View Model
     * @param userId
     */
    public void initViewModel(final int userId) {

        Progress.show(this, "Cargando", "Cargando lista de Posts");
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new PostViewModel(getApplication(), userId);
            }
        };

        postViewModel = ViewModelProviders.of(this, factory).get(PostViewModel.class);
        postViewModel.getListPost().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(@Nullable List<Post> posts) {
                Progress.dismiss();
                postAdapter.setResults(posts);
            }
        });
    }


}
