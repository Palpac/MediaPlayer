package palpac.mediaplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Vbouton1 = null; // Déclarations
    Button Vbouton2 = null;
    Button Vbouton3 = null;

    public MediaPlayer media1;     // Mediaplayer

    public boolean BooleanMedia1IsPlaying; // Flag lecture = true, stopped = false
    public String Audio_File_Path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vbouton1 = findViewById(R.id.Bouton1); // Affectations déclarations au layout
        Vbouton2 = findViewById(R.id.Bouton2);
        Vbouton3 = findViewById(R.id.Bouton3);

        Vbouton1.setOnClickListener(Vbouton1Listener); // Déclaration click listeners
        Vbouton2.setOnClickListener(Vbouton2Listener);
        Vbouton3.setOnClickListener(Vbouton3Listener);

        // ADAPTER LE CHEMIN DU FICHIER AUDIO A UN FICHIER EXISTANT DE VOTER CARTE SD !!!
        Audio_File_Path = Environment.getExternalStorageDirectory().getPath()+"/Documents/Musique/Pop/Inna-Amazing.mp3"; // Chemin fichier audio sdcard

    }

    //////////////////////////////////////////////////////////////////////////////////////////////// Fonctions MEDIAPlAYER
    protected void VerifMedia1Paying (){  // Vérif si mediaplayer en lecture
        if (BooleanMedia1IsPlaying == true) {
            media1.stop();
            media1.release();
            BooleanMedia1IsPlaying = false;
        }
    }
    protected void LanceMedia1 (){ // Lance mediaplayer
        //media1.setLooping(true); // loop
        media1.start();
        BooleanMedia1IsPlaying = media1.isPlaying(); // A n'utiliser qu'après start
    }
    protected void StoppeMedia1 (){ // Stoppe mediaplayer
        if (BooleanMedia1IsPlaying == true) { // Verif si lancé sinon stop plante
            media1.stop();
            Toast.makeText(MainActivity.this, "MediaPlayer stopped", Toast.LENGTH_SHORT).show();
            media1.release();
            BooleanMedia1IsPlaying = false;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// Listeners
    private View.OnClickListener Vbouton1Listener = new View.OnClickListener() { // Listener image 1
        @Override
        public void onClick(View v) {
            VerifMedia1Paying ();
            media1 = MediaPlayer.create(getBaseContext(), R.raw.waouh);   // Chargement fichier audio depuis ressources/raw
            Toast.makeText(MainActivity.this, "Playing from res/raw folder", Toast.LENGTH_SHORT).show();
            LanceMedia1 ();
        }
    };
    private View.OnClickListener Vbouton2Listener = new View.OnClickListener() { // Listener image 1
        @Override
        public void onClick(View v) {
            VerifMedia1Paying ();
            media1 = MediaPlayer.create(getBaseContext(), Uri.parse(Audio_File_Path)); // Chargement audio fichier carte sc
            Toast.makeText(MainActivity.this, Audio_File_Path, Toast.LENGTH_SHORT).show();
            LanceMedia1 ();
        }
    };
    private View.OnClickListener Vbouton3Listener = new View.OnClickListener() { // Listener image 1
        @Override
        public void onClick(View v) {
            StoppeMedia1 ();
        }
    };

}

