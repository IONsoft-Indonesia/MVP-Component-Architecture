package co.id.ionsoft.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.ionsoft.R;
import co.id.ionsoft.data.model.GithubEntity;

/**
 * @author nurhidayat
 * @since 03/05/18.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {


    private List<GithubEntity> listData;
    private OnItemClickListener listener;


    public MainRecyclerViewAdapter(List<GithubEntity> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GithubEntity entity = listData.get(position);
        holder.txtId.setText(String.valueOf(entity.getId()));
        holder.txtName.setText(entity.getFullName());
        holder.txtLang.setText(entity.getLanguage());
        holder.itemView.setOnClickListener(v -> listener.onClick(entity.getCloneUrl()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView txtName;

        @BindView(R.id.id)
        TextView txtId;

        @BindView(R.id.lang)
        TextView txtLang;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void replaceData(List<GithubEntity> listData) {
        this.listData.clear();
        this.listData.addAll(listData);
        notifyDataSetChanged();
    }

    public void clearData() {
        listData.clear();
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onClick(String url);
    }
}
