package blak.android;

import android.text.Editable;
import android.text.TextWatcher;

public class SimpleTextWatcher implements TextWatcher {
    private TextChangedListener mListener;

    public SimpleTextWatcher() {
    }

    public SimpleTextWatcher(TextChangedListener listener) {
        mListener = listener;
    }

    public TextChangedListener getListener() {
        return mListener;
    }

    public void setListener(TextChangedListener listener) {
        mListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (mListener != null) {
            mListener.afterTextChanged(s);
        }
    }

    public static interface TextChangedListener {
        public void afterTextChanged(Editable s);
    }
}
