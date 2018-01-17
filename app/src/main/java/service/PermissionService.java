package service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.CheckResult;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import di.ActivityScope;

/**
 * Created by hossam on 1/17/18.
 */
@ActivityScope
public class PermissionService {
    private final Context context;

    @Inject
    PermissionService(Context context) {
        this.context = context;
    }

    @CheckResult(suggest = "#enforcePermission(String,int,int,String)")
    public boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED;
    }
}
