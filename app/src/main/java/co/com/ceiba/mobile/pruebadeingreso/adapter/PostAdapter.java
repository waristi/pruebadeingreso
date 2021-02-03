package co.com.ceiba.mobile.pruebadeingreso.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.Post;


/**
 * Adaptador de la lista de post
 */
public class PostAdapter extends RecyclerView.Adapter {

    private List<Post> listPost;

    private class PostViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.title);
            tvBody = (TextView) itemView.findViewById(R.id.body);
        }

        void bind(Post post, int position) {
            tvTitle.setText(post.getTitle());
            tvBody.setText(post.getBody());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_list_item, viewGroup, false);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Post post = (Post) listPost.get(i);
        ((PostAdapter.PostViewHolder) viewHolder).bind(post, i);
    }

    @Override
    public int getItemCount() {
        return (listPost != null ? listPost.size() : 0);
    }

    public void setResults(List<Post> listPost) {
        this.listPost = listPost;
        notifyDataSetChanged();
    }
}
