package eu.sesma.paraguas.log

import android.annotation.SuppressLint
import android.os.Environment

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DiskLogger
@Inject

constructor() {
    companion object {
        const val LOG_PATH = "paraguas"
        const val LOG = "paraguas.log"
        const val LOG_BACK = "paraguasBack.log"
        const val MAX_SIZE = 100000
        val PATH: String
            get() {
                var filepath = Environment.getExternalStorageDirectory().absolutePath
                val file = File(filepath, LOG_PATH)
                file.mkdirs()
                filepath = "$filepath/$LOG_PATH"
                return filepath
            }
    }

    private val file: File?
        get() {
            val path = PATH
            var logFile = File(path, LOG)
            if (logFile.length() > MAX_SIZE) {
                val fileBack = File(path, LOG_BACK)
                if (fileBack.exists()) fileBack.delete()
                logFile.renameTo(fileBack)
                logFile = File(path, LOG)
            }
            try {
                logFile.createNewFile()
            } catch (e: IOException) {
                return null
            }

            return logFile
        }


    @SuppressLint("SimpleDateFormat")
    fun log(tag: String?, message: String) {
        val logFile = file ?: return

        try {
            val buf = BufferedWriter(FileWriter(logFile, true))
            val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())
            val text = "$date => $tag: $message"
            buf.append(text)
            buf.newLine()
            buf.close()
        } catch (e: IOException) {
            //Ignore
        }
    }

}
