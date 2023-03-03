package com.vaca.androidreadwritepermissiontest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private fun checkPermission(p_array: List<String>): Boolean {
        for (p in p_array) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    p
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val p_array = listOf<String>(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )

        val requestVoicePermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            readFile()
        }
        if (!checkPermission(p_array)
        ) {
            requestVoicePermission.launch(p_array.toTypedArray())
        } else {
            readFile()
        }
    }

    fun readFile(){
        var root_old = Environment.getExternalStorageDirectory()
        if (root_old == null) {
            root_old = Environment.getDataDirectory()
        }
        val dir_old = File(root_old, "NCIO2/")
        val gg=File(dir_old,"PlusebitO2.db")
        val file = gg
        val text = file.readBytes()
        Log.e("fuck",text.size.toString())
    }
}