package org.leeecs.basic_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BasicAppActivity extends Activity {
  public static final String TAG = "BasicAppActivity";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Started");

    // Set up a basic view
    LinearLayout contentView = new LinearLayout(this);
    contentView.setOrientation(LinearLayout.VERTICAL);
    setContentView(contentView);

    // Create a simple button
    Button simpleButton = new Button(this);
    simpleButton.setText("Simple Button");
    simpleButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "Button Clicked");
      }
    });

    // Add button to view
    contentView.addView(simpleButton);
  }

}