package me.jesuscodes.alias.util.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by Jesus Christ. Amen.
 */
public class EnterBlockingEditText extends EditText {
    public EnterBlockingEditText(Context context) {
        super(context);
    }

    public EnterBlockingEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnterBlockingEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EnterBlockingEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_ENTER || super.onKeyDown(keyCode, event);
    }

}
