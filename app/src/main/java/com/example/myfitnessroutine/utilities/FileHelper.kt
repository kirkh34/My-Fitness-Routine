package com.example.myfitnessroutine.utilities

import android.app.Application
import android.content.Context
import android.util.Log
import java.io.File
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

        fun saveTextToFile(app: Application, json: String?, fileName: String) {
            val file = File(app.cacheDir, fileName)
            file.writeText(json ?: "", Charsets.UTF_8)
            Log.i("device", "wrote")
            Log.i("device", "${json}")
        }

        fun readTextFile(app: Application, fileName: String?): String? {
            val file = File(app.cacheDir, fileName)
            return if (file.exists()){
                file.readText()
            } else "[]"
        }


    }
}