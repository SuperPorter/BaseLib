package com.example.basecoder.Dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basecoder.Base.BaseRecyclerViewAdapter;
import com.example.basecoder.Base.UIRecyclerViewAdapter;
import com.example.basecoder.Dialog.BaseDialog.BaseDialog;
import com.example.basecoder.Dialog.BaseDialog.UIBaseDialogFragment;
import com.example.basecoder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create BY Luck-S ON 11:06
 * Email: fine9987@163.com
 * Package:com.example.basecoder.Dialog
 * Description:
 */
public final class FullMenuDialog {
    public static final class Builder extends UIBaseDialogFragment.Builder<Builder>
            implements View.OnClickListener,
            BaseRecyclerViewAdapter.OnItemClickListener {
        private MenuDialog.OnListener mListener;
        private boolean mAutoDismiss = true;
        private final RecyclerView mRecyclerView;
        private final TextView mTitletv;
        private final ImageView mCloseIv;
        private final FullMenuAdapter mAdapter;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_fullmenu);
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM);
            mRecyclerView = findViewById(R.id.rv_fullmenu_list);
            mTitletv = findViewById(R.id.tv_fullmenu_title);
            mCloseIv = findViewById(R.id.iv_fullmenu_close);
            mAdapter = new FullMenuAdapter(getContext());
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setAdapter(mAdapter);
            mCloseIv.setOnClickListener(this);
        }

        public Builder setList(int... ids) {
            List<String> data = new ArrayList<>(ids.length);
            for (int id : ids) {
                data.add(getString(id));
            }
            return setList(data);
        }

        public Builder setList(String... data) {
            return setList(Arrays.asList(data));
        }

        @SuppressWarnings("all")
        public Builder setList(List data) {
            mAdapter.setData(data);
            return this;
        }

        public Builder setTitle(@StringRes int id) {
            return setTitle(getString(id));
        }

        public Builder setTitle(CharSequence text) {
            mTitletv.setText(text);
            return this;
        }

        public Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        public Builder setListener(MenuDialog.OnListener listener) {
            mListener = listener;
            return this;
        }

        @Override
        public void onClick(View v) {
            if (mAutoDismiss) {
                dismiss();
            }
            if (v == mCloseIv) {
                if (mListener != null) {
                    mListener.onCancel(getDialog());
                }
            }
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
            if (mAutoDismiss) {
                dismiss();
            }
            if (mListener != null) {
                mListener.onSelected(getDialog(), position, mAdapter.getItem(position));
            }
        }
    }

    private static final class FullMenuAdapter extends UIRecyclerViewAdapter<Object> {

        private FullMenuAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public FullMenuDialog.FullMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FullMenuDialog.FullMenuAdapter.ViewHolder();
        }

        final class ViewHolder extends UIRecyclerViewAdapter.ViewHolder {

            private final TextView mTextView;
            private final View mView;

            public ViewHolder() {
                super(R.layout.item_fullmenu);
                mTextView = (TextView) findViewById(R.id.tv_fullmenu_name);
                mView = findViewById(R.id.v_fullmenu_line);
            }

            @Override
            public void onBindView(int position) {
                mTextView.setText(getItem(position).toString());

                if (position == 0) {
                    // 当前是否只有一个条目
                    if (getItemCount() == 1) {
                        mView.setVisibility(View.GONE);
                    } else {
                        mView.setVisibility(View.VISIBLE);
                    }
                } else if (position == getItemCount() - 1) {
                    mView.setVisibility(View.GONE);
                } else {
                    mView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public interface OnListener<T> {

        /**
         * 选择条目时回调
         */
        void onSelected(BaseDialog dialog, int position, T t);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }

}
