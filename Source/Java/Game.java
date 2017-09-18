package cs245.project2;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game
  extends AppCompatActivity
{
  public static final long serialVersionUID = 0L;
  public int cardNum;
  public boolean disableCards;
  public TextView displayScore;
  public Button endgame;
  public boolean firstCardFlipped;
  public ImageButton firstClick;
  public ImageButton[] gameCards;
  public Dialog hsDialog;
  public boolean[] ifCardFlipped;
  public Integer[] imgs;
  public TableRow[] rows;
  public int score;
  public boolean secondCardFlipped;
  public ImageButton secondClick;
  public TableLayout t1;
  public String userName;
  public EditText userText;
  
  public Game() {}
  
  Game(Object[] paramArrayOfObject, InstantReloadException paramInstantReloadException)
  {
    this();
  }
  
  public void cancel(View paramView)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("cancel.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    Toast.makeText(this, "No Score submitted", 0).show();
    this.hsDialog.cancel();
  }
  
  public void compareCards()
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("compareCards.()V", new Object[] { this });
      return;
    }
    if (this.imgs[this.firstClick.getId()].toString().equals(this.imgs[this.secondClick.getId()].toString()))
    {
      this.firstClick.setTag("flipped");
      this.secondClick.setTag("flipped");
      this.firstClick = null;
      this.secondClick = null;
      this.score += 2;
      this.displayScore.setText("Score: " + this.score);
      return;
    }
    if (this.score - 1 <= 0) {}
    for (this.score = 0;; this.score -= 1)
    {
      this.disableCards = true;
      break;
    }
  }
  
  public void endGame(View paramView)
    throws InterruptedException, IOException
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("endGame.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    int j = 1;
    int i = 0;
    while (i < this.gameCards.length)
    {
      if (this.gameCards[i].getTag().equals("default")) {
        j = 0;
      }
      this.gameCards[i].setImageResource(this.imgs[i].intValue());
      i += 1;
    }
    this.firstClick = null;
    this.secondClick = null;
    if (j != 0) {
      highScoreDialog();
    }
    for (;;)
    {
      this.endgame.setClickable(false);
      return;
      Toast.makeText(this, "Score Not Submitted", 0).show();
    }
  }
  
  public void enter(View paramView)
  {
    Object localObject1 = $change;
    if (localObject1 != null)
    {
      ((IncrementalChange)localObject1).access$dispatch("enter.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    this.userName = this.userText.getText().toString();
    if ((this.userName.equals("")) || (this.userName == null))
    {
      this.userName = null;
      Toast.makeText(this, "Not Valid", 0).show();
      return;
    }
    this.hsDialog.cancel();
    paramView = "";
    localObject1 = "";
    Object localObject2 = new File(getFilesDir() + "/h" + this.cardNum + ".txt");
    int j = 0;
    for (;;)
    {
      int k;
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream((File)localObject2)));
        i = 0;
        if (i < 3)
        {
          String str2 = localBufferedReader.readLine();
          localObject2 = (String)localObject1 + str2 + '\n';
          String str1 = "";
          k = 0;
          localObject1 = str1;
          if (k < str2.length())
          {
            if ((str2.charAt(k) != ' ') || (!Character.isDigit(str2.charAt(k + 1)))) {
              break label550;
            }
            localObject1 = str2.substring(k + 1);
          }
          if ((this.score > Integer.parseInt((String)localObject1)) && (j == 0))
          {
            Log.d("USERNAME", this.userName);
            localObject1 = paramView + this.userName + " " + this.score + '\n';
            j = 1;
            k = i;
            paramView = (View)localObject1;
            if (j == 0) {
              break label538;
            }
            k = i;
            paramView = (View)localObject1;
            if (i == 2) {
              break label538;
            }
            paramView = (String)localObject1 + str2 + '\n';
            k = i + 1;
            break label538;
          }
          localObject1 = paramView + str2 + '\n';
          continue;
        }
        if ((1 != 0) && (j != 0))
        {
          openFileOutput("h" + this.cardNum + ".txt", 0).write(paramView.getBytes());
          Toast.makeText(this, "Score Submitted", 0).show();
          return;
        }
      }
      catch (IOException paramView)
      {
        paramView.printStackTrace();
        return;
      }
      openFileOutput("h" + this.cardNum + ".txt", 0).write(((String)localObject1).getBytes());
      return;
      label538:
      int i = k + 1;
      localObject1 = localObject2;
      continue;
      label550:
      k += 1;
    }
  }
  
  public void highScoreDialog()
    throws IOException
  {
    Object localObject = $change;
    if (localObject != null)
    {
      ((IncrementalChange)localObject).access$dispatch("highScoreDialog.()V", new Object[] { this });
      return;
    }
    this.hsDialog = new Dialog(this);
    this.hsDialog.setContentView(2130968620);
    this.hsDialog.setCancelable(false);
    localObject = new WindowManager.LayoutParams();
    ((WindowManager.LayoutParams)localObject).copyFrom(this.hsDialog.getWindow().getAttributes());
    ((WindowManager.LayoutParams)localObject).width = -1;
    ((WindowManager.LayoutParams)localObject).height = -1;
    this.userText = ((EditText)this.hsDialog.findViewById(2131558525));
    if (!isHighScore())
    {
      Toast.makeText(this, "Not a high Score", 0).show();
      return;
    }
    this.hsDialog.show();
    this.hsDialog.getWindow().setAttributes((WindowManager.LayoutParams)localObject);
  }
  
  public View.OnClickListener imageClick(ImageButton paramImageButton)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      return (View.OnClickListener)localIncrementalChange.access$dispatch("imageClick.(Landroid/widget/ImageButton;)Landroid/view/View$OnClickListener;", new Object[] { this, paramImageButton });
    }
    new View.OnClickListener()
    {
      public static final long serialVersionUID = 0L;
      
      public void onClick(View paramAnonymousView)
      {
        IncrementalChange localIncrementalChange = $change;
        if (localIncrementalChange != null) {
          localIncrementalChange.access$dispatch("onClick.(Landroid/view/View;)V", new Object[] { this, paramAnonymousView });
        }
        while (Game.this.disableCards) {
          return;
        }
        if (Game.this.firstClick == null)
        {
          Game.this.firstClick = ((ImageButton)paramAnonymousView);
          Game.this.firstClick.setImageResource(Game.this.imgs[Game.this.firstClick.getId()].intValue());
          Game.this.firstClick.setTag("hold");
          Game.this.firstClick.setClickable(false);
          return;
        }
        Game.this.secondClick = ((ImageButton)paramAnonymousView);
        Game.this.secondClick.setImageResource(Game.this.imgs[Game.this.secondClick.getId()].intValue());
        Game.this.secondClick.setClickable(false);
        Game.this.compareCards();
      }
    };
  }
  
  public boolean isHighScore()
    throws IOException
  {
    boolean bool = true;
    Object localObject = $change;
    if (localObject != null)
    {
      bool = ((Boolean)((IncrementalChange)localObject).access$dispatch("isHighScore.()Z", new Object[] { this })).booleanValue();
      return bool;
    }
    localObject = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getFilesDir() + "/h" + this.cardNum + ".txt"))));
    ((BufferedReader)localObject).readLine();
    ((BufferedReader)localObject).readLine();
    localObject = ((BufferedReader)localObject).readLine();
    int i = 0;
    for (;;)
    {
      if (i >= ((String)localObject).length()) {
        break label177;
      }
      if ((((String)localObject).charAt(i) == ' ') && (Character.isDigit(((String)localObject).charAt(i + 1))))
      {
        i = Integer.parseInt(((String)localObject).substring(i + 1));
        if (this.score > i) {
          break;
        }
        return false;
      }
      i += 1;
    }
    label177:
    return false;
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
  
  public void newGame(View paramView)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("newGame.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    paramView = getIntent();
    finish();
    startActivity(paramView);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("onCreate.(Landroid/os/Bundle;)V", new Object[] { this, paramBundle });
    }
    do
    {
      return;
      super.onCreate(paramBundle);
      setContentView(2130968602);
      paramBundle = getIntent().getExtras();
      boolean bool = paramBundle.getBoolean("muteState");
      ((ToggleButton)findViewById(2131558488)).setChecked(bool);
      this.cardNum = new Scanner(paramBundle.getString("cardCount")).nextInt();
      this.endgame = ((Button)findViewById(2131558487));
      this.score = 0;
      this.disableCards = false;
      this.displayScore = ((TextView)findViewById(2131558490));
      this.t1 = ((TableLayout)findViewById(2131558489));
      this.gameCards = setupGame(this.cardNum);
    } while (getResources().getConfiguration().orientation != 2);
    setCardSize(this.gameCards, this.cardNum);
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
    MyApplication.game = true;
    MyApplication.activityPaused();
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    Object localObject = $change;
    if (localObject != null) {
      ((IncrementalChange)localObject).access$dispatch("onRestoreInstanceState.(Landroid/os/Bundle;)V", new Object[] { this, paramBundle });
    }
    boolean bool2;
    int k;
    do
    {
      return;
      super.onRestoreInstanceState(paramBundle);
      localObject = paramBundle.getIntArray("image");
      boolean[] arrayOfBoolean = paramBundle.getBooleanArray("iCF");
      boolean bool1 = paramBundle.getBoolean("fCC");
      bool2 = paramBundle.getBoolean("sCC");
      int j = paramBundle.getInt("tID1");
      k = paramBundle.getInt("tID2");
      this.score = paramBundle.getInt("score");
      this.disableCards = paramBundle.getBoolean("disable");
      this.displayScore.setText("Score: " + this.score);
      int i = 0;
      while (i < this.imgs.length)
      {
        this.imgs[i] = Integer.valueOf(localObject[i]);
        i += 1;
      }
      i = 0;
      while (i < this.gameCards.length)
      {
        if (arrayOfBoolean[i] != 0)
        {
          this.gameCards[i].setImageResource(this.imgs[this.gameCards[i].getId()].intValue());
          this.gameCards[i].setClickable(false);
          this.gameCards[i].setTag("flipped");
        }
        i += 1;
      }
      if (bool1)
      {
        this.gameCards[j].setImageResource(this.imgs[j].intValue());
        this.firstClick = this.gameCards[j];
        this.firstClick.setClickable(false);
      }
    } while (!bool2);
    this.gameCards[k].setImageResource(this.imgs[k].intValue());
    this.secondClick = this.gameCards[k];
    this.secondClick.setClickable(false);
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
    MyApplication.game = false;
    MyApplication.activityResumed();
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    Object localObject = $change;
    if (localObject != null)
    {
      ((IncrementalChange)localObject).access$dispatch("onSaveInstanceState.(Landroid/os/Bundle;)V", new Object[] { this, paramBundle });
      return;
    }
    super.onSaveInstanceState(paramBundle);
    localObject = new int[this.imgs.length];
    int i = 0;
    while (i < this.imgs.length)
    {
      localObject[i] = this.imgs[i].intValue();
      i += 1;
    }
    this.ifCardFlipped = new boolean[this.gameCards.length];
    i = 0;
    while (i < this.gameCards.length)
    {
      if (this.gameCards[i].getTag() == "flipped") {
        this.ifCardFlipped[i] = true;
      }
      i += 1;
    }
    i = -1;
    int j = -1;
    if (this.firstClick == null)
    {
      this.firstCardFlipped = false;
      if (this.secondClick != null) {
        break label255;
      }
      this.secondCardFlipped = false;
    }
    for (;;)
    {
      paramBundle.putIntArray("image", (int[])localObject);
      paramBundle.putBooleanArray("iCF", this.ifCardFlipped);
      paramBundle.putBoolean("fCC", this.firstCardFlipped);
      paramBundle.putBoolean("sCC", this.secondCardFlipped);
      paramBundle.putInt("tID1", i);
      paramBundle.putInt("tID2", j);
      paramBundle.putInt("score", this.score);
      paramBundle.putBoolean("disable", this.disableCards);
      return;
      this.firstCardFlipped = true;
      i = this.firstClick.getId();
      break;
      label255:
      this.secondCardFlipped = true;
      j = this.secondClick.getId();
    }
  }
  
  public void retry(View paramView)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null)
    {
      localIncrementalChange.access$dispatch("retry.(Landroid/view/View;)V", new Object[] { this, paramView });
      return;
    }
    if (this.secondClick != null)
    {
      this.firstClick.setImageResource(2130837590);
      this.secondClick.setImageResource(2130837590);
      this.firstClick.setTag("default");
      this.secondClick.setTag("default");
      this.firstClick.setClickable(true);
      this.secondClick.setClickable(true);
      this.firstClick = null;
      this.secondClick = null;
      this.disableCards = false;
      return;
    }
    Toast.makeText(this, "Choose another card", 0).show();
  }
  
  public void setCardSize(ImageButton[] paramArrayOfImageButton, int paramInt)
  {
    IncrementalChange localIncrementalChange = $change;
    if (localIncrementalChange != null) {
      localIncrementalChange.access$dispatch("setCardSize.([Landroid/widget/ImageButton;I)V", new Object[] { this, paramArrayOfImageButton, new Integer(paramInt) });
    }
    for (;;)
    {
      return;
      Toast.makeText(this, "LandScape", 0).show();
      int i = 0;
      while (i < paramInt)
      {
        paramArrayOfImageButton[i].getLayoutParams().height = 188;
        paramArrayOfImageButton[i].getLayoutParams().width = 160;
        paramArrayOfImageButton[i].requestLayout();
        i += 1;
      }
    }
  }
  
  public ImageButton[] setupGame(int paramInt)
  {
    Object localObject = $change;
    if (localObject != null) {
      return (ImageButton[])((IncrementalChange)localObject).access$dispatch("setupGame.(I)[Landroid/widget/ImageButton;", new Object[] { this, new Integer(paramInt) });
    }
    this.userName = "";
    this.displayScore.setText("Score: " + this.score);
    localObject = new Integer[10];
    localObject[0] = Integer.valueOf(2130837587);
    localObject[1] = Integer.valueOf(2130837588);
    localObject[2] = Integer.valueOf(2130837589);
    localObject[3] = Integer.valueOf(2130837591);
    localObject[4] = Integer.valueOf(2130837592);
    localObject[5] = Integer.valueOf(2130837593);
    localObject[6] = Integer.valueOf(2130837594);
    localObject[7] = Integer.valueOf(2130837606);
    localObject[8] = Integer.valueOf(2130837608);
    localObject[9] = Integer.valueOf(2130837605);
    Collections.shuffle(Arrays.asList((Object[])localObject));
    this.imgs = new Integer[paramInt];
    int j = 0;
    int i = 0;
    while (i < paramInt / 2)
    {
      Integer[] arrayOfInteger = this.imgs;
      k = j + 1;
      arrayOfInteger[j] = localObject[i];
      arrayOfInteger = this.imgs;
      j = k + 1;
      arrayOfInteger[k] = localObject[i];
      i += 1;
    }
    Collections.shuffle(Arrays.asList(this.imgs));
    if (paramInt < 10) {
      this.rows = new TableRow[2];
    }
    for (i = paramInt / 2;; i = 4)
    {
      j = 0;
      while (j < this.rows.length)
      {
        this.rows[j] = new TableRow(this);
        this.rows[j].setLayoutParams(new TableRow.LayoutParams(-1, -1));
        j += 1;
      }
      this.rows = new TableRow[paramInt / 4 + 1];
    }
    localObject = new ImageButton[paramInt];
    int k = 0;
    j = 0;
    while (j < paramInt)
    {
      localObject[j] = new ImageButton(this);
      localObject[j].setId(j);
      localObject[j].setTag("default");
      localObject[j].setLayoutParams(new TableRow.LayoutParams(-2, -2));
      localObject[j].setPadding(0, 0, 0, 0);
      localObject[j].setImageResource(2130837590);
      localObject[j].setScaleType(ImageView.ScaleType.FIT_XY);
      localObject[j].getLayoutParams().height = 290;
      localObject[j].getLayoutParams().width = 190;
      localObject[j].setOnClickListener(imageClick(localObject[j]));
      this.rows[k].addView(localObject[j]);
      int m = k;
      if ((j + 1) % i == 0)
      {
        m = k;
        if (j != 0)
        {
          this.t1.addView(this.rows[k], new TableLayout.LayoutParams(-2, -2));
          m = k + 1;
        }
      }
      j += 1;
      k = m;
    }
    if ((paramInt >= 10) && (paramInt % 4 != 0)) {
      this.t1.addView(this.rows[k], new TableLayout.LayoutParams(-2, -2));
    }
    return (ImageButton[])localObject;
  }
}
