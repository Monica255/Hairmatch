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
            Log.d("error",image.toString())
            Log.d("error",data.toString())
        }catch (e:Exception){
            Log.d("error",e.message.toString())
        }

        if(image!=null){
            val imageUri = Uri.parse(image)
            data?.let {
                with(binding){
                    ivImage.setImageURI(imageUri)
                    // type
                    tvType1.text = it.topThree[0].classLabel.capitalize()
                    tvType2.text = it.topThree[1].classLabel.capitalize()
                    tvType3.text = it.topThree[2].classLabel.capitalize()

                    // style
                    tvStyle1.text = getString(R.string.style_format, it.topThree[0].recommendation.capitalize())
                    tvStyle2.text = getString(R.string.style_format, it.topThree[1].recommendation.capitalize())
                    tvStyle3.text = getString(R.string.style_format, it.topThree[2].recommendation.capitalize())
                }
            }
        }

        binding.btRestart.setOnClickListener {
            finish()
        }
    }
}