package ariel.sv.com.recyclerview_ejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private List<String> names;
    private RecyclerView myReclyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       try{
           names= this.getAllNames();
           myReclyclerView = (RecyclerView) findViewById(R.id.My_recycler_view);
           myAdapter = new MyAdapter(names, R.layout.recycler_view_item,new MyAdapter.onItemClickListener(){
               @Override
               public void onItemClick(String name, int position) {
                   //Toast.makeText(MainActivity.this,name + " - "+ position,Toast.LENGTH_LONG).show();
                   deleteName(position);

               }
           });
           //todos los tipos de layout manager con los que se puede jugar con el recycler view
           myLayoutManager = new LinearLayoutManager(this);
           myLayoutManager = new GridLayoutManager(this,2);
           myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
           //solo en caso que sepamos que el tamaño del layout no va a cambiar ahrehgamos esto e incrementa el rendimiento, aunque de nada sirve con el StraggeredGridLayout porque cambia los tamaños
           //myReclyclerView.setHasFixedSize(true);
           //animacion por defecto
           myReclyclerView.setItemAnimator(new DefaultItemAnimator());

           myReclyclerView.setLayoutManager(myLayoutManager);
           myReclyclerView.setAdapter(myAdapter);
       }catch(Exception e)
       {
           System.out.println("nose :" + e.getMessage());
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private List<String> getAllNames(){
        return new ArrayList<String>(){{
            add("Alejandro");
            add("Jose");
            add("Barrera");
            add("Ruben");
            add("Antonio");
        }};
    }
    private void addName(int position){
        names.add(position, "new name "+ (++counter));
        myAdapter.notifyItemInserted(position);
        myLayoutManager.scrollToPosition(position);

    }

    private void deleteName(int position){
        names.remove(position);
        myAdapter.notifyItemRemoved(position);
    }
}
