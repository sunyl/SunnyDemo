package com.widget.lib.utils;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;

/**
 * 简化Adapter中ViewHolder的使用, view获取
 * @author zuijinbuzai
 *
 */
public class ViewHolder {

	 @SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		 SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		 if (viewHolder == null) {  
			 viewHolder = new SparseArray<>();
			 view.setTag(viewHolder);  
        }  
        View childView = viewHolder.get(id);
        if (childView == null) {  
        	childView = view.findViewById(id);  
            viewHolder.put(id, childView);  
        }  
        return (T) childView;  
	 }

    public static <T extends View> T findViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    public static <T extends View> T findViewById(Activity activity, int id) {
        return (T) activity.findViewById(id);
    }
}