package com.example.hairmatch.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hairmatch.R
import com.example.hairmatch.core.data.remote.model.Response
import com.example.hairmatch.databinding.ActivityResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    private var image:String? =null
    private var data: Response?=null
    private lateinit var binding:ActivityResultBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            image = intent.getStringExtra("image")
            data = intent.getParcelableExtra("data", Response::class.java)
        }catch (e:Exception){
            Log.d("error",e.message.toString())
        }

        if(image!=null){
            val imageUri = Uri.parse(image)
            data?.let {
                with(binding){
                    tvTitle.text = it.prediction.classLabel.capitalize()
                    ivImage.setImageURI(imageUri)

                    // style
                    tvStyle1.text = getString(R.string.style_format, it.prediction.recommendation[0].capitalize())
                    tvStyle2.text = getString(R.string.style_format, it.prediction.recommendation[1].capitalize())
                    tvStyle3.text = getString(R.string.style_format, it.prediction.recommendation[2].capitalize())
                }
            }
        }

        binding.btRestart.setOnClickListener {
            finish()
        }
    }
}