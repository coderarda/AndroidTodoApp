package com.example.androidtodopractice

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import java.lang.ClassCastException
import java.lang.IllegalStateException
import java.lang.RuntimeException

class TodoItemAdapter(
    context: Context,
    @LayoutRes private val layoutResource: Int,
    @IdRes private val textViewResId: Int = 0,
    private val values: List<String>
) : ArrayAdapter<String>(context, layoutResource, values) {

    private fun createViewFromResource(
        convertView: View?,
        parent: ViewGroup,
        layoutResource: Int
    ): LinearLayout {
        val ctx = parent.context
        val view = convertView ?: LayoutInflater.from(ctx).inflate(layoutResource, parent, false)
        return try {
            if (textViewResId == 0) view as LinearLayout
            else {
                view.findViewById(textViewResId) ?: throw RuntimeException(
                    "Failed to find view with ID." + "${
                        context.resources.getResourceName(
                            textViewResId
                        )
                    } in item layout"
                )
            }
        } catch (ex: ClassCastException) {
            Log.e("CustomArrayAdapter", "TextView must have an ID.")
            throw IllegalStateException("ArrayAdapter requires the resource id to be a LinearLayout.", ex)
        }
    }

    private fun bindData(value: String, view: LinearLayout): LinearLayout {
        val textview = view.findViewById<TextView>(R.id.item_text_view)
        textview.text = value
        view.findViewById<CheckBox>(R.id.check_box).setOnCheckedChangeListener { _: CompoundButton, b: Boolean ->
            if (b) textview.setTextColor(Color.GRAY) else textview.setTextColor(Color.BLACK)
            textview.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG xor textview.paintFlags
        }
        return view
    }

    override fun getItem(position: Int): String = values[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = createViewFromResource(convertView, parent, layoutResource)

        return bindData(getItem(position), view)
    }


}