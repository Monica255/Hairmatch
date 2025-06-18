package com.example.hairmatch.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
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
            data?.let { data->
                with(binding){
                    tvTitle.text = data.prediction.classLabel.capitalize()
                    ivImage.setImageURI(imageUri)

                    // style
                    tvStyle1.text = getString(R.string.style_format, data.prediction.recommendation[0].name.capitalize())
                    tvStyle2.text = getString(R.string.style_format, data.prediction.recommendation[1].name.capitalize())
                    tvStyle3.text = getString(R.string.style_format, data.prediction.recommendation[2].name.capitalize())

                    cvStyle1.setOnClickListener{
                        goToFilter(data.prediction.recommendation[0].urlFilter.toUri())
                    }
                    cvStyle2.setOnClickListener{
                        goToFilter(data.prediction.recommendation[1].urlFilter.toUri())
                    }
                    cvStyle3.setOnClickListener{
                        goToFilter(data.prediction.recommendation[2].urlFilter.toUri())
                    }
                }
            }
        }

        binding.btRestart.setOnClickListener {
            finish()
        }
    }

    fun goToFilter(filterUri:Uri){
        val intent = Intent(Intent.ACTION_VIEW, filterUri).apply {
            setPackage("com.snapchat.android")
        }
        try {
            startActivity(intent)
        }catch (e:Exception ){
            Toast.makeText(this, "Snapchat app not found", Toast.LENGTH_SHORT).show()
        }
    }
}