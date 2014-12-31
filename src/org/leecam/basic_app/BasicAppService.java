package org.leecam.basic_app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class BasicAppService extends Service {

  public static final String TAG = "BasicAppService";

  static final int MSG_SAY_HELLO = 1;

  private native void sayHello();

  @Override
  public void onCreate() {
    Log.i(TAG,"BasicAppService Created");
  }

  class MessageHandler extends Handler {
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case MSG_SAY_HELLO:
          Log.i(TAG, "MSG_SAY_HELLO");
          sayHello();
          break;
        default:
          super.handleMessage(msg);
      }
    }
  }

  Messenger mMessenger = new Messenger(new MessageHandler());

  @Override
  public IBinder onBind(Intent intent) {
    Log.i(TAG,"BasicAppService Binding");
    return mMessenger.getBinder();
  }

  static {
    System.loadLibrary("native-lib");
  }
}