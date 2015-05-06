/*
 * Copyright (C) 2015 Pedro Vicente Gomez Sanchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.pedrovgs.nox;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.github.pedrovgs.nox.imageloader.ImageLoader;

/**
 * ImageLoader.Listener implementation used to notify NoxItemcCatalog when a NoxItem is ready to be
 * used.
 *
 * @author Pedro Vicente Gomez Sanchez.
 */
class NoxItemCatalogImageLoaderListener implements ImageLoader.Listener {

  private final int position;
  private final NoxItemCatalog noxItemCatalog;

  NoxItemCatalogImageLoaderListener(int position, NoxItemCatalog noxItemCatalog) {
    this.position = position;
    this.noxItemCatalog = noxItemCatalog;
  }

  @Override public void onPlaceholderLoaded(Drawable placeholder) {
    noxItemCatalog.setDefaultPlaceholder(position, placeholder);
    noxItemCatalog.notifyNoxItemReady(position);
  }

  @Override public void onImageLoaded(Bitmap image) {
    noxItemCatalog.setBitmap(position, image);
    noxItemCatalog.setLoading(position, false);
    noxItemCatalog.notifyNoxItemReady(position);
  }

  @Override public void onError() {
    noxItemCatalog.setLoading(position, false);
  }

  @Override public void onDrawableLoaded(Drawable drawable) {
    noxItemCatalog.setDrawable(position, drawable);
    noxItemCatalog.setLoading(position, false);
    noxItemCatalog.notifyNoxItemReady(position);
  }

  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NoxItemCatalogImageLoaderListener)) {
      return false;
    }

    NoxItemCatalogImageLoaderListener that = (NoxItemCatalogImageLoaderListener) o;

    return position == that.position;
  }

  @Override public int hashCode() {
    return position;
  }
}
