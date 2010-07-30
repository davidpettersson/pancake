/*
 * Copyright (C) 2010 David Pettersson
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
package se.avendi.pancake.ui;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.TextView;

class CardFaceAdapter extends BaseAdapter {

    private final Context context;
    private final String[] faces = new String[]{
        "0", "\u00BD", "1", "2", "3", "5", "8", "13", "20", "40", "100", "\u221E", "\u2668", "?",};

    public CardFaceAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return faces.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        /*
         * TODO: This entire snippet is really ugly and should
         *   be rewritten.
         */
        TextView t = new TextView(context);
        t.setLayoutParams(new Gallery.LayoutParams(parent.getWidth(), parent.getHeight()));
        t.setText(faces[position]);
        t.setBackgroundColor(0xff000000);
        t.setTextColor(0xffffffff);
        t.setGravity(Gravity.CENTER);
        t.setSingleLine();

        boolean fits = false;
        float k = 0.6f;

        while (!fits && k > 0.0f) {
            t.setTextSize(TypedValue.COMPLEX_UNIT_PX, parent.getHeight() * k);

            TextPaint tp = new TextPaint(t.getPaint());
            Rect r = new Rect();
            tp.getTextBounds(faces[position], 0, faces[position].length(), r);

            fits = r.width() < (parent.getWidth() * 0.8f);
            k -= 0.05f;

            Log.d("X", "fits=" + fits + ", k=" + k);
        }

        return t;
    }
}
