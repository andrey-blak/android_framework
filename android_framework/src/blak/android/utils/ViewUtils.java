package blak.android.utils;

import android.graphics.Paint;
import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewUtils {
    public static void resetHardwareAcceleration(View view) {
        // setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // but hardware acceleration was added only in API 11
        if (Build.VERSION.SDK_INT >= 11) {
            Class viewClass = View.class;
            try {
                Method setLayerType = viewClass.getDeclaredMethod("setLayerType", int.class, Paint.class);
                Field layerTypeSoftwareField = viewClass.getDeclaredField("LAYER_TYPE_SOFTWARE");
                int layerTypeSoftwareValue = layerTypeSoftwareField.getInt(view);
                setLayerType.invoke(view, layerTypeSoftwareValue, null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public static void show(View view, boolean show) {
        int visibility = (show) ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }
}
