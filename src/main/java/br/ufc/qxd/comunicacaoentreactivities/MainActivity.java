package br.ufc.qxd.comunicacaoentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.qxd.Classes.Musica;

public class MainActivity extends AppCompatActivity {
    private int contadorId = 0;
    private ArrayList<Musica> musicas = new ArrayList<Musica>();
    private Button btnAdd;
    private Button btnEdt;
    private ListView listaMusicas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.hasExtra("musicas")){
            musicas = intent.getParcelableArrayListExtra("musicas");
            contadorId = musicas.size();
        }


        listaMusicas = (ListView) findViewById(R.id.listaMusicas);

        ArrayAdapter<Musica> musicasAdapter = new ArrayAdapter<Musica>(this,
                android.R.layout.simple_list_item_1, musicas);
        listaMusicas.setAdapter(musicasAdapter);

        abrirAdd();
        abrirEdt();
    }

    public void abrirAdd(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contadorId++;

                Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                intent.putExtra("id", contadorId);
                intent.putParcelableArrayListExtra("musicas", musicas);
                startActivity(intent);
            }
        });

    }
    public void abrirEdt(){

        btnEdt = (Button) findViewById(R.id.btnEdt);
        btnEdt.setOnClickListener(new View.OnClickListener() {

            @Override
                public void onClick(View view) {
                EditText edId = (EditText) findViewById(R.id.idMsc);
                    if(!edId.getText().toString().equals("")) {
                        if(verificaId(Integer.parseInt(edId.getText().toString()))) {
                            Musica msc = getMusicaById(Integer.parseInt(edId.getText().toString()));
                            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
                            intent.putExtra("id", msc.getId());
                            intent.putExtra("nome", msc.getNome());
                            intent.putExtra("autor", msc.getAutor());
                            intent.putExtra("album", msc.getAlbum());
                            intent.putParcelableArrayListExtra("musicas", musicas);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Digite um ID válido", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Digite um ID válido", Toast.LENGTH_SHORT).show();
                    }
            }
            });
    }
    public Musica getMusicaById(int id){
        for (int i = 0; i < musicas.size(); i++) {
            if(musicas.get(i).getId() == id){ return musicas.get(i); }
        }
        return null;
    }
    public boolean verificaId(int id){
        for (int i = 0; i < musicas.size(); i++) {
            if(musicas.get(i).getId() == id){ return true; }
        }
        return false;
    }
}