package omnia.musicplayer;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;



/**
 * Created by Lenovo-pc on 24/06/2017.
 */

public class PlayerFragment extends Fragment {
    View v;
    MediaPlayer player;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.player_fragment,container,false);
        final EditText url=(EditText)v.findViewById(R.id.url);
        Button play=(Button)v.findViewById(R.id.button);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlstring=url.getText().toString();
                if(!urlstring.isEmpty()) {
                    Log.e("omnia","true");
                    if(player!=null){
                        player.stop();
                    }
                    new playerAsync().execute(urlstring);
                }
                else{
                    Log.e("omnia",urlstring);
                    Toast.makeText(getActivity(),"Url can not be empty",Toast.LENGTH_LONG);
                }
            }
        });


        return v;
    }
    private class playerAsync extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            /*MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(),R.raw.song);
            mediaPlayer.setVolume(1.0f,1.0f);
            mediaPlayer.start();*/
            Log.e("omnia",params[0]);
            player=new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                player.setDataSource(params[0]);
                player.setVolume(1.0f,1.0f);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
