package br.ufc.qxd.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Musica implements Parcelable {
    private int id;
    private String nome;
    private String autor;
    private String album;

    public Musica(int id, String nome, String autor, String album) {
        this.id = id;
        this.nome = nome;
        this.autor = autor;
        this.album = album;
    }

    public Musica(){

    }

    protected Musica(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        autor = in.readString();
        album = in.readString();
    }

    public static final Creator<Musica> CREATOR = new Creator<Musica>() {
        @Override
        public Musica createFromParcel(Parcel in) {
            return new Musica(in);
        }

        @Override
        public Musica[] newArray(int size) {
            return new Musica[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return id + " | " + nome +
                " | Álbum: " + album + " | Autor: " + autor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nome);
        parcel.writeString(autor);
        parcel.writeString(album);
    }
}
