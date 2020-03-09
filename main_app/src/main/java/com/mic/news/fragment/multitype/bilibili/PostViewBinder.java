/*
 * Copyright 2016 drakeet. https://github.com/drakeet
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

package com.mic.news.fragment.multitype.bilibili;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.thirdparty.multitype.ItemViewBinder;


/**
 * @author drakeet
 */
public class PostViewBinder extends ItemViewBinder<Post, PostViewBinder.ViewHolder> {

  @Override
  protected @NonNull ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.item_post, parent, false));
  }


  @Override
  protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Post post) {
    holder.setData(post);
  }


  static class ViewHolder extends RecyclerView.ViewHolder {

    private @NonNull
    ImageView cover;
    private @NonNull
    TextView title;


    ViewHolder(@NonNull View itemView) {
      super(itemView);
      cover = itemView.findViewById(R.id.cover);
      title = itemView.findViewById(R.id.title);
    }


    void setData(@NonNull final Post post) {
      cover.setImageResource(post.coverResId);
      title.setText(post.title);
    }
  }
}
