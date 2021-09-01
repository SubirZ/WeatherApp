package com.example.weatherapp.utils.jsonadaptors

import android.graphics.PointF
import com.squareup.moshi.*
import com.squareup.moshi.internal.Util

class PointFJsonAdapter : JsonAdapter<PointF>() {

    private val options: JsonReader.Options = JsonReader.Options.of("x", "y")

    private val moshi = Moshi.Builder().build()

    private val xAdapter: JsonAdapter<Float> = moshi.adapter(Float::class.java, emptySet(), "x")
    private val yAdapter: JsonAdapter<Float> = moshi.adapter(Float::class.java, emptySet(), "y")

    override fun toString(): String = buildString(26) {
        append("GeneratedJsonAdapter(").append("PointF").append(')')
    }

    @FromJson
    override fun fromJson(reader: JsonReader): PointF {
        var x: Float? = null
        var y: Float? = null

        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                0 -> {
                    x = xAdapter.fromJson(reader) ?: throw Util.unexpectedNull("x", "x", reader)
                }
                1 -> {
                    y = xAdapter.fromJson(reader) ?: throw Util.unexpectedNull("y", "y", reader)
                }
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        return PointF(
            x ?: throw Util.missingProperty("x", "x", reader),
            y ?: throw Util.missingProperty("y", "y", reader)
        )
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: PointF?) {
        if (value == null) {
            throw NullPointerException("value was null! Wrap in .nullSafe() to write nullable values.")
        }
        writer.beginObject()
        writer.name("x")
        xAdapter.toJson(writer, value.x)
        writer.name("y")
        yAdapter.toJson(writer, value.y)
        writer.endObject()
    }
}