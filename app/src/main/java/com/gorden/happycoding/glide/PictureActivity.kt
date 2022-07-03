package com.gorden.happycoding.glide

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gorden.happycoding.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPictureBinding.inflate(layoutInflater)
    }

    private val picUrl =
        "https://tse1-mm.cn.bing.net/th/id/R-C.39cbdb6e9eb314e71a170193bfd6fa79?rik=bGFDyf0lBANc%2fA&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20160817%2fcfacdebe24fccb3eb2638971ca2ae412.jpg&ehk=mFA5dLU0hSqBZ2UwsAmOT0CNk0MjmWvygojM%2bUE407Y%3d&risl=&pid=ImgRaw&r=0"

    val picUrl2 =
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Ftravel.ulifestyle.com.hk%2Fnews%2Fdetail%2F23520%2F%25E8%25A5%25BF%25E8%2597%258F%25E6%2597%25A5%25E8%2588%2587%25E5%25A4%259C-%25E7%25B5%2595%25E7%25BE%258E%25E6%2598%259F%25E7%25A9%25BA%25E9%258A%2580%25E6%25B2%25B3-%25E6%2597%25A5%25E7%2585%25A7%25E9%2587%2591%25E5%25B1%25B1&psig=AOvVaw1nHQQ81rPelpx9UJjyQRv0&ust=1653338956440000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCJiIwt798_cCFQAAAAAdAAAAABAO"

    private val gifUrl = "https://img.zcool.cn/community/0194275c189bdaa80121ab5d53759e.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 手机试验加载图片，只支持 https ！
        Glide.with(this).load(picUrl)
            .into(binding.picIv)

        val glide = Glide.get(this)

        val requestManagerRetriever = glide.requestManagerRetriever

        val requestManager = requestManagerRetriever.get(this)

        val requestBuilderDrawable = requestManager.asDrawable()

        val requestBuilder = requestBuilderDrawable.load(picUrl)

        requestBuilder.into(binding.picIv)



//        binding.picIv.post {
//            binding.picTv.text = "${binding.picIv.drawable.toBitmap().byteCount}"
//        }
        kotlin.run {
            /*
            1. 构建请求实例
            2。加载链接
            3。处理缓存图片，加载到控件中
             */
            Glide.with(applicationContext).load(gifUrl)
                .into(binding.picIv)
        }
    }
}