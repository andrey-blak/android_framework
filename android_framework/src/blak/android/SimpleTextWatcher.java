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
        mListener = (listener != null) ? listener : NULL_TEXT_CHANGED_LISTENER;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        mListener.afterTextChanged(s);
    }

    public static interface TextChangedListener {
        public void afterTextChanged(Editable s);
    }

    public static final TextChangedListener NULL_TEXT_CHANGED_LISTENER = new TextChangedListener() {
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
