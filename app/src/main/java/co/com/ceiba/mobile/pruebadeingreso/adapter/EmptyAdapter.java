package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.com.ceiba.mobile.pruebadeingreso.R;

public class EmptyAdapter extends RecyclerView.Adapter<EmptyAdapter.UserViewHolder> {

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
