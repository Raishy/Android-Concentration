package cs245.project2;

import android.app.Application;
import android.media.MediaPlayer;
import android.util.Log;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class MyApplication
  extends Application
{
  public static boolean game = true;
  public static boolean highscore = true;
  public static boolean inHome = false;
  public static boolean menu = true;
  public static final long serialVersionUID = 0L;
  
  public MyApplication() {}
  
  MyApplication(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException) {}
  
  public static void activityPaused()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("activityPaused.()V", new Object[0]);
      return;
    }
    checkGame();
  }
  
  public static void activityResumed()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("activityResumed.()V", new Object[0]);
      return;
    }
    resumeGame();
  }
  
  public static void checkGame()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("checkGame.()V", new Object[0]);
    }
    do
    {
      return;
      Log.d("what", "MENU: " + menu + "\nGAME: " + game + "\nhighscore: " + highscore);
    } while ((!menu) || (!game) || (!highscore));
    BackgroundSoundService.player.pause();
    inHome = true;
  }
  
  public static void resumeGame()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("resumeGame.()V", new Object[0]);
    }
    while (!inHome) {
      return;
    }
    if (BackgroundSoundService.toggle == 1) {
      BackgroundSoundService.player.start();
    }
    inHome = false;
  }
}
