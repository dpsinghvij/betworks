package com.davinder.betworks.login

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.davinder.betworks.R
import com.davinder.betworks.views.InputEditText
import com.davinder.betworks.views.ViewItem

class LoginAdapter(var viewItems: List<ViewItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewItem.EDIT_TEXT -> InputEditTextHolder.from(parent)
            ViewItem.BUTTON -> LoginButtonHolder.from(parent)
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewItem = viewItems[position]
        when (holder) {
            is LoginButtonHolder -> holder.bind(viewItem)
            is InputEditTextHolder -> holder.bind(viewItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewItems[position].getViewType()
    }

    override fun getItemCount(): Int = viewItems.size

    class InputEditTextHolder(private val editText: InputEditText) :
        RecyclerView.ViewHolder(editText) {
        fun bind(item: ViewItem) {
            (item as? ViewItem.InputViewItem)?.let {
                editText.setData(viewItem = item)
            }
        }

        companion object {
            fun from(parent: ViewGroup): InputEditTextHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val row =
                    layoutInflater.inflate(R.layout.cell_edit_text, parent, false) as InputEditText
                return InputEditTextHolder((row))
            }
        }
    }

    class LoginButtonHolder(private val button: Button) : RecyclerView.ViewHolder(button) {
        fun bind(item: ViewItem) {
            (item as? ViewItem.ButtonViewItem)?.let {
                button.text = item.text
            }
        }

        companion object {
            fun from(parent: ViewGroup): LoginButtonHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val row = layoutInflater.inflate(R.layout.cell_button, parent, false) as Button
                return LoginButtonHolder((row))
            }
        }
    }
}