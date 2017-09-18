package cs245.project2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;

public class BackgroundSoundService
  extends Service
{
  private static final String TAG = null;
  public static boolean musicPlaying;
  public static boolean orientationChange;
  public static MediaPlayer player;
  public static final long serialVersionUID = 0L;
  public static int toggle;
  public int playerPosition;
  
  public BackgroundSoundService() {}
  
  BackgroundSoundService(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException) {}
  
  public static void muteSong()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("muteSong.()V", new Object[0]);
      return;
    }
    if (toggle == 1)
    {
      player.pause();
      Log.d("INMUTE", "stop");
      musicPlaying = false;
    }
    for (toggle = 0;; toggle = 1)
    {
      Log.d("INMUTE", "END");
      return;
      player.start();
      musicPlaying = true;
      Log.d("INMUTE", "start");
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      return (IBinder)localIncrementalChange.access$dispatch("onBind.(Landroid/content/Intent;)Landroid/os/IBinder;", new Object[] { this, paramIntent });
    }
    return null;
  }
  
  public void onCreate()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onCreate.()V", new Object[] { this });
      return;
    }
    toggle = 1;
    musicPlaying = true;
    orientationChange = false;
    super.onCreate();
    Log.d(TAG, "Music service is created onCreate()");
    player = MediaPlayer.create(this, 2131099648);
    player.setLooping(true);
    player.setVolume(100.0F, 100.0F);
  }
  
  public void onDestroy()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onDestroy.()V", new Object[] { this });
      return;
    }
    super.onDestroy();
    player.stop();
    player.release();
    Log.d(TAG, "Player destroyed");
  }
  
  public void onLowMemory()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("onLowMemory.()V", new Object[] { this });
    }
  }
  
  public void onPause()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("onPause.()V", new Object[] { this });
    }
  }
  
  public void onResume()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("onResume.()V", new Object[] { this });
    }
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("onStart.(Landroid/content/Intent;I)V", new Object[] { this, paramIntent, new Integer(paramInt) });
    }
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      return ((Number)localIncrementalChange.access$dispatch("onStartCommand.(Landroid/content/Intent;II)I", new Object[] { this, paramIntent, new Integer(paramInt1), new Integer(paramInt2) })).intValue();
    }
    if (player.isPlaying())
    {
      Log.d(TAG, "Player is already playing!");
      player.seekTo(this.playerPosition);
    }
    for (;;)
    {
      Log.d(TAG, "Player started!");
      return 2;
      player.seekTo(this.playerPosition);
      player.start();
    }
  }
  
  public IBinder onUnBind(Intent paramIntent)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      return (IBinder)localIncrementalChange.access$dispatch("onUnBind.(Landroid/content/Intent;)Landroid/os/IBinder;", new Object[] { this, paramIntent });
    }
    return null;
  }
}
