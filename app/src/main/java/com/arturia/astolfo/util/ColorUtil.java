package com.arturia.astolfo.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;

/**
 * Author: lybeat
 * Date: 2016/9/17
 */
public class ColorUtil {

    public static int getColor(Context context, int colorId) {
        Resources resources = context.getResources();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return resources.getColor(colorId, context.getTheme());
        }
        return resources.getColor(colorId);
    }
}
