/*  Nextcloud Android Library is available under MIT license
 *   Copyright (C) 2017 Joas Schilling
 *
 *   @author Joas Schilling
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 *   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 *   BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 *   ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *   CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 *
 */
package com.owncloud.android.lib.resources.activities.models;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * PreviewList Parser
 */

public class PreviewListAdapter extends TypeAdapter<PreviewList> {

    @Override
    public void write(JsonWriter out, PreviewList value) throws IOException {

    }

    @Override
    public PreviewList read(JsonReader in) throws IOException {
        PreviewList previewList = new PreviewList();
        in.beginArray();

        while (in.hasNext()) {
            String name = in.nextName();
            if (name != null && !name.isEmpty()) {
                previewList.getPreviews().add(readObject(name,in));
            }
        }


        in.endArray();

        return previewList;
    }

    PreviewObject readObject(String tag,JsonReader in) throws IOException {
        in.beginObject();
        PreviewObject preview = new PreviewObject();
        while (in.hasNext()) {
            String name = in.nextName();
            if ("source".contentEquals(name)){
                preview.setSource(in.nextString());
            } else if ("link".contentEquals(name)) {
                preview.setLink(in.nextString());
            } else if ("isMimeTypeIcon".contentEquals(name)) {
                preview.setMimeTypeIcon(in.nextBoolean());
            }
        }
        in.endObject();
        return preview;
    }
}

