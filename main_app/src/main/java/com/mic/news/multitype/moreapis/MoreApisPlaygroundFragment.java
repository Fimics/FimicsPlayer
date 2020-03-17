package com.mic.news.multitype.moreapis;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.bb.frame.common.BaseFragment;
import com.mic.news.multitype.normal.TextItem;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.thirdparty.multitype.Items;
import com.mic.thirdparty.multitype.MultiTypeAdapter;

import static android.os.SystemClock.currentThreadTimeMillis;
import static java.lang.String.valueOf;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreApisPlaygroundFragment extends BaseFragment {
    private static final String TERMINAL_DEFAULT_TEXT = "ObservableTextItemViewBinder: ";

    private TextView terminal;
    private RecyclerView recyclerView;

    @VisibleForTesting
    MultiTypeAdapter adapter;
    @VisibleForTesting
    Items items;

    public MoreApisPlaygroundFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        recyclerView = rootView.findViewById(R.id.list);
        terminal = rootView.findViewById(R.id.terminal);
        rootView.findViewById(R.id.btn_add).setOnClickListener(view -> onAdd());
        rootView.findViewById(R.id.btn_remove).setOnClickListener(view -> onRemove());
        rootView.findViewById(R.id.btn_clear).setOnClickListener(view -> onClear());
        terminal.setText(TERMINAL_DEFAULT_TEXT);

        items = new Items();
        adapter = new MultiTypeAdapter();

        adapter.register(TextItem.class, new ObservableTextItemViewBinder());
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 200; i++) {
            items.add(new TextItem(valueOf(i)));
        }
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more_apis_playground;
    }

    public void onAdd() {
        int bottom = items.size() - 1;
        items.add(new TextItem(valueOf(currentThreadTimeMillis())));
        adapter.notifyItemInserted(bottom + 1);
        recyclerView.scrollToPosition(bottom + 1);
    }


    public void onRemove() {
        int bottom = items.size() - 1;
        recyclerView.scrollToPosition(bottom);
        items.remove(bottom);
        adapter.notifyItemRemoved(bottom);
    }


    public void onClear() {
        items.clear();
        adapter.notifyDataSetChanged();
    }


    private class ObservableTextItemViewBinder extends ItemViewBinder<TextItem, ObservableTextItemViewBinder.TextHolder> {

        class TextHolder extends RecyclerView.ViewHolder {

            private @NonNull
            final TextView text;


            TextHolder(@NonNull View itemView) {
                super(itemView);
                this.text = itemView.findViewById(R.id.text);
            }
        }


        @NonNull
        @Override
        protected TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
            View root = inflater.inflate(R.layout.item_text, parent, false);
            return new TextHolder(root);
        }


        @Override
        protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull TextItem textItem) {
            holder.text.setText("observable item(" + textItem.text + ")");
        }


        @Override
        protected void onViewRecycled(@NonNull TextHolder holder) {
            appendTerminalLine("onViewRecycled: " + holder.text.getText());
        }


        @Override
        protected boolean onFailedToRecycleView(@NonNull TextHolder holder) {
            appendTerminalLine("onFailedToRecycleView: " + holder.text.getText());
            return true;
        }


        @Override
        protected void onViewAttachedToWindow(@NonNull TextHolder holder) {
            appendTerminalLine("onViewAttachedToWindow: " + holder.text.getText());
        }


        @Override
        protected void onViewDetachedFromWindow(@NonNull TextHolder holder) {
            appendTerminalLine("onViewDetachedFromWindow: " + holder.text.getText());
        }


        private int buffer = 0;


        private void appendTerminalLine(String line) {
            if (buffer == 5) {
                terminal.setText(TERMINAL_DEFAULT_TEXT);
                buffer = 0;
            }
            terminal.append("\n" + line);
            buffer++;
        }
    }

}
