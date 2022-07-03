package com.gorden.happycoding.view

import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gorden.happycoding.R
import com.gorden.happycoding.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    companion object{
        private val TAG = "GG"
    }

    private val binding by lazy{
        ActivityViewBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val view = LayoutInflater.from(this).inflate(R.layout.view_inflate, binding.viewLl, true)

        val handler = MyHandler()

        val message = Message.obtain()
        message.isAsynchronous = false
        message.what = 0
        handler.sendMessage(message)

        view.post {
            val textView = TextView(this)
            textView.text = "添加的view"
            binding.root.addView(textView)
        }
    }

    class MyHandler : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                0 ->{
                    SystemClock.sleep(3000)
                    Log.e(TAG, "handleMessage: 异步信息")
                }

            }
        }
    }
}
