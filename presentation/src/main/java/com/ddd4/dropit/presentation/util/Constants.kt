package com.ddd4.dropit.presentation.util

import android.Manifest

object Constants {

    //Permission Request Code
    const val PERMISSION_CODE_CAPTURE = 1001

    val PERMISSION_MANIFEST_CAPTURE =
        listOf(Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE)

    //Intent Request Code
    const val INTENT_CODE_IMAGE = 3000

    const val INTENT_MOVE_FOLDER = 1500
    const val INTENT_ITEM_DETAIL = 3030

    //Extra Value ID
    const val EXTRA_NAME_FOLDER_ID = "folderId"
    const val EXTRA_NAME_CATEGORY_ID = "categoryId"
    const val EXTRA_NAME_IMAGE_PATH = "imagePath"
    const val EXTRA_NAME_ITEM_ID = "itemId"

    const val EXTRA_DELETE_ITEM = "deleteItem"
    const val EXTRA_DDAY = "dday"
}