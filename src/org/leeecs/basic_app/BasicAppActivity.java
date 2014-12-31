package org.leeecs.basic_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BasicAppActivity extends Activity {
  public static final String TAG = "BasicAppActivity";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Started");
  }

}