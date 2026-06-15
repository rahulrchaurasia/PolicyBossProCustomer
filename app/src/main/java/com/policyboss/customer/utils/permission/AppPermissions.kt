package com.policyboss.customer.utils.permission

import android.Manifest

object AppPermissions {

    val CAMERA = arrayOf(Manifest.permission.CAMERA)

    val CONTACTS = arrayOf(
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.READ_CALL_LOG
    )

    val MEDIA = arrayOf(
        Manifest.permission.READ_MEDIA_IMAGES
    )
}