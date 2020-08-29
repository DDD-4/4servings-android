package com.ddd4.dropit.presentation.ui.folder

interface ItemHandler {
    fun <T> onItemClicked(item: T, visibility: Boolean)

    fun <T> onItemDetailClicked(item: T)
}