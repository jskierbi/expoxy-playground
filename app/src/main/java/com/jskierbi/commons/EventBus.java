package com.jskierbi.commons;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.VisibleForTesting;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventBus {

  private static Bus busInstance;
  private static Bus busInstanceMainThread;
  private static boolean flgAlwaysSameThread = false;

  public static Bus getDefault() {
    if (busInstance == null) {
      busInstance = new Bus(ThreadEnforcer.ANY);
    }
    return busInstance;
  }

  public static Bus ui() {
    if (flgAlwaysSameThread) return getDefault();
    if (busInstanceMainThread == null) {
      busInstanceMainThread = new BusMainThread(getDefault());
    }
    return busInstanceMainThread;
  }

  public static void register(Object obj) {
    getDefault().register(obj);
  }

  public static void unregister(Object obj) {
    getDefault().unregister(obj);
  }

  @VisibleForTesting
  public static void setDebugAlwaysSameThread(boolean alwaysSameThread) {
    EventBus.flgAlwaysSameThread = alwaysSameThread;
  }

  /**
   * Bus for posting directly on main thread
   */
  private static class BusMainThread extends Bus {
    private final Bus mBus;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public BusMainThread(final Bus bus) {
      if (bus == null) {throw new NullPointerException("bus must not be null");}
      mBus = bus;
    }

    @Override
    public void register(final Object obj) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        mBus.register(obj);
      } else {
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            mBus.register(obj);
          }
        });
      }
    }

    @Override
    public void unregister(final Object obj) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        mBus.unregister(obj);
      } else {
        mHandler.post(new Runnable() {
          @Override
          public void run() {
            mBus.unregister(obj);
          }
        });
      }
    }

    @Override
    public void post(final Object event) {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        mBus.post(event);
      } else {
        mHandler.post(new Runnable() {
          @Override
          public void run() { mBus.post(event);}
        });
      }
    }
  }
}
