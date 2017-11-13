package com.mindnotix.fabriccrash;

/**
 * Created by Admin on 11/13/2017.
 */


        import android.app.Application;
        import android.content.pm.PackageInfo;
        import android.content.pm.PackageManager;
        import android.content.pm.Signature;
        import android.util.Base64;
        import android.util.Log;

        import com.crashlytics.android.Crashlytics;

        import java.security.MessageDigest;
        import java.security.NoSuchAlgorithmException;

        import io.fabric.sdk.android.Fabric;

/**
 * Created by Belal on 5/3/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        printHashKey();
    }

    public void printHashKey(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "net.simplifiedcoding.androidlogin",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}