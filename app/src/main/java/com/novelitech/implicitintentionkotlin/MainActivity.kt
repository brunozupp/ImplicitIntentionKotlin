package com.novelitech.implicitintentionkotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.novelitech.implicitintentionkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnGallery.setOnClickListener {

            // .ACTION_GET_CONTENT = it involves every type of content, so I need to specify
            val intentGallery = Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
            }

            resultLauncher.launch(intentGallery)
        }
    }

    // By being a ACTION_GET_CONTENT, the .data information is an URI
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
        if(result.resultCode == Activity.RESULT_OK) {

            val data: Intent? = result.data

            val uri = data?.data

            if(uri != null) {
                binding.ivPhoto.setImageURI(uri)
            }
        }
    }
}