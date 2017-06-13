package ariel.sv.com.recyclerview_ejemplo;


import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ariel on 12/6/2017.
 */

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    private List<String> names;
    private int layout;
    private onItemClickListener listener;

    public MyAdapter(List<String> names, int layout, onItemClickListener listener) {
        this.names = names;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView textViewName;

        public ViewHolder(View v){
            super(v);
            this.textViewName =(TextView) v.findViewById(R.id.tvReclycer);
        }

        public void bind(final String name, final  onItemClickListener listener){
            this.textViewName.setText(name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(name, getAdapterPosition());
                }
            });

        }

    }

    public interface onItemClickListener{
        void onItemClick(String name, int position);
    }
}
