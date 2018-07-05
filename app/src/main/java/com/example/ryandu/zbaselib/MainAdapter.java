package com.example.ryandu.zbaselib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * <p>Project:BaseLibDemo</p>
 * <p>Package:com.duyangs.baselibdemo</p>
 * <p>Description:</p>
 * <p>Company:</p>
 *
 * @author duyangs
 * @date 2017/05/02 0002
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private String[] type;

    public MainAdapter(Context context, String[] type) {
        this.context = context;
        this.type = type;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.button.setText(type[position]);
        if (onItemClickListener != null){
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(v,holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (type == null) ? 0 : type.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            button = (Button)itemView.findViewById(R.id.button);
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }
}
