package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.User;

public class UserAdater extends RecyclerView.Adapter {

    /*private Context mContext;*/
    private List<User> listUser;

    /*public UserAdater(Context mContext, List<User> listUser) {
        this.mContext = mContext;
        this.listUser = listUser;
    }*/

    private class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvPhone, tvEmail;
        Button btnPost;
        User user;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvPhone = (TextView) itemView.findViewById(R.id.phone);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            btnPost.setOnClickListener(this);
        }

        void bind(User user, int position){
            this.user = user;
            tvName.setText(user.getName());
            tvPhone.setText(user.getPhone());
            tvEmail.setText(user.getEmail());
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item, viewGroup, false);
        return new UserAdater.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        User user = (User) listUser.get(i);
        ((UserAdater.UserViewHolder) viewHolder).bind(user, i);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public void setResults(List<User> listUser) {
        this.listUser = listUser;
        notifyDataSetChanged();
    }

}
