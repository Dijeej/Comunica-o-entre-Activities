package br.ufc.qxd.comunicacaoentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.qxd.Classes.Musica;

public class AddEditActivity extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextNome;
    private EditText editTextAutor;
    private EditText editTextAlbum;
    private ArrayList<Musica> musicas;
    private boolean edicao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        Intent intent = getIntent();
        musicas = intent.getParcelableArrayListExtra("musicas");
        editTextId = (EditText) findViewById(R.id.idMusica);
        editTextNome = (EditText) findViewById(R.id.nomeMusica);
        editTextAutor = (EditText) findViewById(R.id.autorMusica);
        editTextAlbum = (EditText) findViewById(R.id.albumMusica);

        editTextId.setText( "" + intent.getIntExtra("id", 0));

        if(intent.getStringExtra("nome") != null){
            edicao = true;
            editTextNome.setText(intent.getStringExtra("nome"));
            editTextAutor.setText(intent.getStringExtra("autor"));
            editTextAlbum.setText(intent.getStringExtra("album"));
        }
        aplicar();
        cancelar();
    }
    public void aplicar(){
        Button btnAplicar = (Button) findViewById(R.id.btnAplicar);
        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextId = (EditText) findViewById(R.id.idMusica);
                editTextNome = (EditText) findViewById(R.id.nomeMusica);
                editTextAutor = (EditText) findViewById(R.id.autorMusica);
                editTextAlbum = (EditText) findViewById(R.id.albumMusica);

                int id = Integer.parseInt(editTextId.getText().toString());
                String nome = editTextNome.getText().toString();
                String autor = editTextAutor.getText().toString();
                String album = editTextAlbum.getText().toString();

                Musica msc = new Musica(id, nome, autor, album);
                if(!(edicao)) {
                    musicas.add(msc);
                }else{
                    for (int i = 0; i < musicas.size(); i++) {
                        if (musicas.get(i).getId() == msc.getId()) {
                            musicas.get(i).setNome(msc.getNome());
                            musicas.get(i).setAutor(msc.getAutor());
                            musicas.get(i).setAlbum(msc.getAlbum());
                            break;
                        }
                    }
                }
                Intent intent = new Intent(AddEditActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("musicas", musicas);

                startActivity(intent);
            }
        });

    }
    public void cancelar(){
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddEditActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("musicas", musicas);
                startActivity(intent);
            }
        });
    }
}