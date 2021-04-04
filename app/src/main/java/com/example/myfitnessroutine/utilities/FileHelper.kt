package com.example.myfitnessroutine.utilities

import android.content.Context
import java.security.AccessControlContext

class FileHelper {
    companion object{
        fun getTextFromAssets(context: Context, fileName: String): String {
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }
    }
}