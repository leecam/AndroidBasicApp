package org.leecam.basic_app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class BasicAppActivity extends Activity {
  public static final String TAG = "BasicAppActivity";
  boolean mBound;
  Messenger mService;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "Started");

    // Set up a basic view
    LinearLayout contentView = new LinearLayout(this);
    contentView.setOrientation(LinearLayout.VERTICAL);
    setContentView(contentView);

    // Create a simple button
    Button startServiceButton = new Button(this);
    startServiceButton.setText("Start Service");
    final BasicAppActivity ctx = this;
    startServiceButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "Starting Service");
        bindService(new Intent(ctx, BasicAppService.class),
                    mConnection,
                    Context.BIND_AUTO_CREATE);
      }
    });
    // Add button to view
    contentView.addView(startServiceButton);
    contentView.addView(
        createRemoteMessageButton("Service - Say Hello",
                                  BasicAppService.MSG_SAY_HELLO));
  }

  // Creates a button to send a message to the service
  // This boiler plate code was stolen from rsesek!
  private Button createRemoteMessageButton(final String label,
                                           final int messageID) {
    Button button = new Button(this);
    button.setText(label);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (mBound) {
          try {
            mService.send(Message.obtain(null, messageID, 0, 0));
          } catch (RemoteException ex) {
            Log.e(TAG, ex.getMessage(), ex);
          }
        } else {
          Log.e(TAG, "Need to start service");
        }
      }
    });
    return button;
  }

  private ServiceConnection mConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName className, IBinder service) {
      mService = new Messenger(service);
      mBound = true;
    }
    @Override
    public void onServiceDisconnected(ComponentName className) {
      mService = null;
      mBound = false;
    }
  };

}