package ariel.sv.com.recyclerview_ejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;
    private RecyclerView myReclyclerView;
    private RecyclerView.Adapter myAdapter;
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
                   Toast.makeText(MainActivity.this,name + " - "+ position,Toast.LENGTH_LONG).show();


               }
           });
           myReclyclerView.setLayoutManager(new LinearLayoutManager(this));
           myReclyclerView.setAdapter(myAdapter);
       }catch(Exception e)
       {
           System.out.println("nose :" + e.getMessage());
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
}
