package com.ram.systemtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.ram.systemtest.R;

public class ResultContainerView extends CardView {

    TextView txtResult;
    ProgressBar progressLoader;

    public ResultContainerView(Context context) {
        super(context);
    }

    public ResultContainerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ResultContainerView, 0, 0);
        String strResult = typedArray.getString(R.styleable.ResultContainerView_text);
        boolean loader = typedArray.getBoolean(R.styleable.ResultContainerView_loader, false);
        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.result_container_view, this, true);

        txtResult = findViewById(R.id.txtResult);
        progressLoader = findViewById(R.id.progressLoader);

        if (loader) {
            showProgress(loader);
        }

        if (!TextUtils.isEmpty(strResult)) {
            setTxtResult(strResult);
        }
    }

    public ResultContainerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showProgress(boolean isShow) {
        if (isShow) {
            progressLoader.setVisibility(VISIBLE);
            txtResult.setText("");
            txtResult.setVisibility(GONE);
        } else {
            progressLoader.setVisibility(GONE);
            txtResult.setVisibility(VISIBLE);
        }
    }

    public void setTxtResult(String str) {
        showProgress(false);
        txtResult.setVisibility(VISIBLE);
        txtResult.setText(str);
    }
}
