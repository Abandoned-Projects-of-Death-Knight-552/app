package com.knight.moonreaderdatabase.utils

import android.text.TextUtils
import android.webkit.URLUtil
import android.widget.Toast

object Validator {
    fun validateEntry(
        title: String,
        download: String,
        synopsis: String?,
        coverRemote: String?,
        coverLocal: String?,
    ): Int {
        if (title.trim().isEmpty()) {
            return 110
        }
        if (download.trim().isEmpty()) {
            return 120
        } else if (!URLUtil.isValidUrl(download.trim())) {
            return 121
        }

        if (coverRemote != null) {
            if (!coverRemote.trim().isEmpty()) {
                if (!URLUtil.isValidUrl(coverRemote.trim())) {
                    return 130
                }
            }
        }




        return 200
    }
}