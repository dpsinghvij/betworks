package com.davinder.betworks.views

import com.davinder.betworks.views.ViewItem.InputViewItem
import junit.framework.TestCase

class ViewItemTest : TestCase() {

    public override fun setUp() {
        super.setUp()
    }

    fun `test invalid input when all text no number`() {
        val inputViewItem = getInputViewItem("abcdd")
        assertFalse(inputViewItem.validate())
    }

    fun `test invalid input when all text all numbers`() {
        val inputViewItem = getInputViewItem("2123231")
        assertFalse(inputViewItem.validate())
    }

    fun `test valid input when text has numbers and alphabet`() {
        val inputViewItem = getInputViewItem("2123231adadf")
        assertTrue(inputViewItem.validate())
    }

    fun `test valid input when text has numbers alphabet random postions`() {
        val inputViewItem = getInputViewItem("a1db31")
        assertTrue(inputViewItem.validate())
    }

    fun `test invalid input when text has empty string`() {
        val inputViewItem = getInputViewItem("")
        assertFalse(inputViewItem.validate())
    }

    private fun getInputViewItem(value: String) = InputViewItem(id = "", value = value, hint = "")
}