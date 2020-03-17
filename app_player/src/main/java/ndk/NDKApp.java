package ndk;

import android.app.Application;

public class NDKApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NDKTools.loadLibrary();
        NDKInterface.signatureVerify(this);
    }
}
