package com.example.root.memternet;

import android.app.Application;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

public class App extends Application {
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleApiClient mGoogleApiClient;

    public String getId() {
        OptionalPendingResult<GoogleSignInResult> opr =
                Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        GoogleSignInResult result = opr.await();
        if (result.isSuccess()) {
            try {
                return result.getSignInAccount().getIdToken();
            } catch (NullPointerException e) {
                return "-1";
            }
        } else {
            return "-1";
        }
    }
}
