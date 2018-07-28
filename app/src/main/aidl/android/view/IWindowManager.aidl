// IDisplayManager.aidl
package android.view;

import android.graphics.Point;

// Declare any non-default types here with import statements
interface IWindowManager {

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void getBaseDisplaySize(int i, out Point point);

}
