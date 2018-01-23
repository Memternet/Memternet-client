package com.example.root.memternet;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class Token {
    public static GoogleSignInClient mGoogleSignInClient;
    public static GoogleApiClient mGoogleApiClient;

    public static String getId() {
        OptionalPendingResult<GoogleSignInResult> pendingResult =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (pendingResult.isDone()) {
            return pendingResult.get().getSignInAccount().getIdToken();
        }
        try {
            pendingResult.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    Log.d("Google Auth", googleSignInResult.getSignInAccount().getIdToken());
                }
            });
            return "-1";
        } catch (NullPointerException e) {
            return "";
        }
    }
}
