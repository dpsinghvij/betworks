package com.davinder.betworks.views

sealed class ViewItem {

    abstract fun getViewType(): Int

    data class InputViewItem(val id: String, var value: String, val hint: String) : ViewItem() {

        override fun getViewType() = EDIT_TEXT

        fun validate(): Boolean {
            return validatorRegex.matches(value)
        }

        companion object {
            val validatorRegex = "^(?=.*\\d)(?=.*[a-zA-Z]).{2,}\$".toRegex()
        }
    }
    
    data class ButtonViewItem(val id: String, var text: String) : ViewItem() {
        override fun getViewType() = BUTTON
    }

    companion object {
        const val EDIT_TEXT = 1
        const val BUTTON = 2
    }
}