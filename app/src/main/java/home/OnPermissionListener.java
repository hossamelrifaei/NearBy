package home;

/**
 * Created by hossam on 1/15/18.
 */

public interface OnPermissionListener {
    boolean HasPermission();

    void requestPermission();
}
