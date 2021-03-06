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

package com.mic.find.multitype.weibo;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author drakeet
 */
final class WeiboJsonParser {

  private WeiboJsonParser() {}


  static final Gson GSON = new GsonBuilder()
      .registerTypeAdapter(WeiboContent.class, new WeiboContentDeserializer())
      .create();


  static List<Weibo> fromJson(@NonNull String json) {
    return GSON.fromJson(json, new TypeToken<ArrayList<Weibo>>() {}.getType());
  }
}
