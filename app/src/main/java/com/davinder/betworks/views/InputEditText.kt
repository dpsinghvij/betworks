package com.davinder.betworks.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class InputEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatEditText(context, attrs, defStyleAttr) {

    var viewItem: ViewItem.InputViewItem? = null

    fun setData(viewItem: ViewItem.InputViewItem) {
        this.viewItem = viewItem
    }

    private val textChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            viewItem?.run {
                value = editable.toString()
            }
        }
    }

    init {
        addTextChangedListener(textChangeListener)
    }

}

