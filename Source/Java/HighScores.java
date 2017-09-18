package cs245.project2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HighScores
  extends AppCompatActivity
{
  public static final long serialVersionUID = 0L;
  public int card;
  public TextView score;
  
  public HighScores() {}
  
  HighScores(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException)
  {
    this();
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
    setContentView(2130968603);
    paramBundle = (TextView)findViewById(2131558492);
    this.score = ((TextView)findViewById(2131558494));
    paramBundle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Paperland.ttf"));
    this.card = new Scanner(getIntent().getExtras().getString("cardCount")).nextInt();
    readFile();
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
    MyApplication.highscore = true;
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
    Log.d("HIGHSCORE", "when are u called?");
    MyApplication.highscore = false;
    MyApplication.activityResumed();
  }
  
  public void readFile()
  {
    Object localObject1 = $change;
    if (localObject1 != null)
    {
      ((IncrementalChange)localObject1).access$dispatch("readFile.()V", new Object[] { this });
      return;
    }
    Object localObject3 = new File(getFilesDir() + "/h" + this.card + ".txt");
    localObject1 = "";
    Object localObject2 = localObject1;
    try
    {
      localObject3 = new BufferedReader(new InputStreamReader(new FileInputStream((File)localObject3)));
      int i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= 3) {
          break;
        }
        localObject2 = localObject1;
        String str = ((BufferedReader)localObject3).readLine();
        localObject2 = localObject1;
        localObject1 = (String)localObject1 + str + '\n';
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
      this.score.setText((CharSequence)localObject2);
      this.score.setTextSize(20.0F);
    }
  }
}
