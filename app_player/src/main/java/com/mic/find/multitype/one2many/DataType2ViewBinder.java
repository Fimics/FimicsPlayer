/*
 * Copyright 2016  https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mic.find.multitype.one2many;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.core.thirdparty.multitype.ItemViewBinder;


/**
 * Note: Data - DataType2ViewBinder
 *
 * @author drakeet
 */
public class DataType2ViewBinder extends ItemViewBinder<Data, DataType2ViewBinder.ViewHolder> {

  @Override
  protected @NonNull ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.item_data_type2, parent, false);
    return new ViewHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull DataType2ViewBinder.ViewHolder holder, @NonNull Data data) {
    holder.setTitle(data.title);
  }


  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView titleView;


    ViewHolder(View itemView) {
      super(itemView);
      titleView = itemView.findViewById(android.R.id.title);
    }


    void setTitle(String title) {
      titleView.setText(title);
    }
  }
}
