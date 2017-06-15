package ariel.sv.com.recyclerview_ejemplo;


import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ariel on 12/6/2017.
 */

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.ViewHolder>{


    private List<Movie> movies;
    private int layout;
    private onItemClickListener listener;

    public MyAdapter(List<Movie> movies, int layout, onItemClickListener listener) {
        this.movies = movies;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inlfamos el layout y le pasamos lso datos al constructor del view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //llamamos al metodo bind del viewholder pasandole el objdeto y un listener
        holder.bind(movies.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        //Elementos UI a rellenar
        public TextView textViewmovie;
        public ImageView imageViewmovie;

        public ViewHolder(View v){

            //recibe la vista completa para que la rendericemos, pasamos parametros a constructor padre
            // aqui tambien manejamos los datos de logioca para extraer datos y hacer referencias con los elementoss
            super(v);
            this.textViewmovie =(TextView) v.findViewById(R.id.textViewPoster);
            this.imageViewmovie = (ImageView) v.findViewById(R.id.imageViewPoster);
        }

        public void bind(final Movie movie, final  onItemClickListener listener){
            //procesamos los datos para renderizar
            textViewmovie.setText(movie.getName());
            imageViewmovie.setImageResource(movie.getPoster());
           // this.textViewmovie.setText(movie.getName());
            /// definimos que por cada elemento del recycler view tenemos un listener que se va a comportart de la siguiente manera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(movie, getAdapterPosition());
                }
            });

        }

    }
    ///declaramos las interfaces con los metodos a implementar
    public interface onItemClickListener{
        void onItemClick(Movie movie, int position);
    }
}
