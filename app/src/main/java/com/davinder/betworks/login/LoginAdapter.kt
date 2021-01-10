package com.davinder.betworks.login

import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.davinder.betworks.views.InputEditText
import com.davinder.betworks.views.ViewItem
import java.lang.IllegalArgumentException

class LoginAdapter(var viewItems: List<ViewItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewItem.EDIT_TEXT -> InputEditTextHolder(InputEditText(parent.context))
            ViewItem.BUTTON -> LoginButtonHolder(Button(parent.context))
            else -> throw IllegalArgumentException("Invalid ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewItem = viewItems[position]
        when(holder) {
            is LoginButtonHolder -> holder.bind(viewItem)
            is InputEditTextHolder -> holder.bind(viewItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewItems[position].getViewType()
    }

    override fun getItemCount(): Int = viewItems.size

    class InputEditTextHolder(private val editText: InputEditText): RecyclerView.ViewHolder(editText) {
        fun bind(item: ViewItem) {
            (item as? ViewItem.InputViewItem)?.let {
                editText.setData(viewItem = item)
            }
        }
    }

    class LoginButtonHolder(private val button: Button): RecyclerView.ViewHolder(button) {
        fun bind(item: ViewItem) {
            fun bind(item: ViewItem) {
                (item as? ViewItem.ButtonViewItem)?.let {
                    button.text = item.text
                }
            }
        }
    }
}