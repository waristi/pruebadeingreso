package co.com.ceiba.mobile.pruebadeingreso.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapter.EmptyAdapter;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserAdapter;
import co.com.ceiba.mobile.pruebadeingreso.helpers.Progress;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.viewmodel.UserViewModel;

/**
 * ACTIVIDAD PRINCIPAL
 * Contiene la lista de usuarios y opcion de busquedad
 * Aruitectura MVVP
 * @author Bernardo Alexander ZUluaga Aristizabal
 */
public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private UserAdapter userAdapter;
    private EmptyAdapter emptyAdapter;

    EditText editTextSearch;
    private RecyclerView recyclerViewSearchResults;

    List<User> listUserSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de Usuarios");
        initSeacrh();
        initList();
        initViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Buscador de usuarios
     */
    private void initSeacrh() {
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();

                if(search != ""){
                    List<User> listTmp = new ArrayList<User>();
                    if(listUserSearch != null){
                        for(User user: listUserSearch){
                            if(user.getName().toLowerCase().contains(search.toLowerCase())){
                                listTmp.add((user));
                            }
                        }

                        if(listTmp.isEmpty()){
                            emptyAdapter = new EmptyAdapter();
                            recyclerViewSearchResults.setAdapter(emptyAdapter);
                        }else{
                            recyclerViewSearchResults.setAdapter(userAdapter);
                        }

                        userAdapter.setResults(listTmp);
                    }
                }else{
                    userAdapter.setResults(listUserSearch);
                    recyclerViewSearchResults.setAdapter(userAdapter);
                }
            }
        });
    }

    /**
     * Inicializa la lista
     */
    public void initList() {
        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearchResults.setHasFixedSize(true);

        userAdapter = new UserAdapter();
        recyclerViewSearchResults.setAdapter(userAdapter);
    }

    /**
     * Inicializa el ViewModel
     */
    public void initViewModel() {
        Progress.show(this, "Cargando", "Cargando lista de usuarios");
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getListUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                Progress.dismiss();
                userAdapter.setResults(users);
                listUserSearch = users;
            }
        });
    }


}