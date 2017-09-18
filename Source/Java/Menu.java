package cs245.project2;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Menu
  extends AppCompatActivity
{
  public static final long serialVersionUID = 0L;
  public boolean onPauseM;
  public int orientation;
  public Intent playbackIntent;
  public boolean rotated;
  
  public Menu() {}
  
  Menu(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException)
  {
    this();
  }
  
  private boolean isMyServiceRunning(Class<?> paramClass)
  {
    Object localObject = $change;
    if (localObject != null) {
      return ((Boolean)((IncrementalChange)localObject).access$dispatch("isMyServiceRunning.(Ljava/lang/Class;)Z", new Object[] { this, paramClass })).booleanValue();
    }
    localObject = ((ActivityManager)getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)((Iterator)localObject).next();
      if (paramClass.getName().equals(localRunningServiceInfo.service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  public void createFiles()
    throws IOException
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("createFiles.()V", new Object[] { this });
      return;
    }
    int i = 4;
    label29:
    if (i <= 20) {
      if (!new File(getFilesDir() + "/h" + i + ".txt").exists()) {
        break label88;
      }
    }
    for (;;)
    {
      i += 2;
      break label29;
      break;
      label88:
      openFileOutput("h" + i + ".txt", 0).write("AAA 3\nBBB 2\nCCC 1".getBytes());
    }
  }
  
  public void highScores(View paramView)
  {
    Object localObject = $change;
    if (localObject != null)
    {
      ((IncrementalChange)localObject).access$dispatch("highScores.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    paramView = (Spinner)findViewById(2131558497);
    localObject = new Intent(this, HighScores.class);
    ((Intent)localObject).putExtra("cardCount", paramView.getSelectedItem().toString());
    startActivity((Intent)localObject);
  }
  
  public void muteSong(View paramView)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("muteSong.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    BackgroundSoundService.muteSong();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onCreate.(Landroid/os/Bundle;)V", new Object[] { this, paramBundle });
      return;
    }
    super.onCreate(paramBundle);
    setContentView(2130968604);
    ((TextView)findViewById(2131558445)).setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Paperland.ttf"));
    if (!isMyServiceRunning(BackgroundSoundService.class))
    {
      this.playbackIntent = new Intent(this, BackgroundSoundService.class);
      startService(this.playbackIntent);
      Log.d("service", "NOT RUNNING");
    }
    for (;;)
    {
      this.onPauseM = false;
      this.rotated = false;
      try
      {
        createFiles();
        return;
      }
      catch (Exception paramBundle)
      {
        return;
      }
      Log.d("service", "IS RUNNING");
    }
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
    if (isFinishing())
    {
      Log.d("DESTROY", "ISFINISHING TRUE");
      return;
    }
    BackgroundSoundService.orientationChange = true;
  }
  
  public void onPause()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onPause.()V", new Object[] { this });
      return;
    }
    super.onPause();
    MyApplication.menu = true;
    MyApplication.activityPaused();
  }
  
  public void onResume()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onResume.()V", new Object[] { this });
      return;
    }
    super.onResume();
    MyApplication.menu = false;
    MyApplication.activityResumed();
  }
  
  public void onStop()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("onStop.()V", new Object[] { this });
      return;
    }
    super.onStop();
    Log.d("test", "pls");
  }
  
  public void playGame(View paramView)
  {
    Object localObject = $change;
    if (localObject != null)
    {
      ((IncrementalChange)localObject).access$dispatch("playGame.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    paramView = (ToggleButton)findViewById(2131558498);
    localObject = (Spinner)findViewById(2131558497);
    Intent localIntent = new Intent(this, Game.class);
    localIntent.putExtra("muteState", paramView.isChecked());
    localIntent.putExtra("cardCount", ((Spinner)localObject).getSelectedItem().toString());
    startActivity(localIntent);
  }
}
