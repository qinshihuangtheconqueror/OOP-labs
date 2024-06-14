import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Utils {
    public MediaPlayer mediaPlayer;
    public String media;
    public Utils(String media){
        Media m = new Media(media);
        MediaPlayer a =  new MediaPlayer(m);
        a.setOnEndOfMedia(new Runnable() {
            public void run() {
                a.seek(Duration.ZERO);
            }
        });
        this.mediaPlayer =a;
    }
}
