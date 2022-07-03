package com.gorden.happycoding.retrofit

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.gorden.happycoding.databinding.ActivityRetrofitBinding
import com.gorden.happycoding.okhttp.OkhttpActivity
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    retrofi 是对 OKhttp的封装，所以在我们了解retrofit之前，先需要熟悉OKhttp的使用以及使用的痛点！
 */

// get 方法：https://wanandroid.com/wxarticle/chapters/json 获取公众号历史文章

/*
步骤：
1。 定义一个api接口
2。 构建一个 Retrofit 实例
3。 动态创建接口实例
4。 调用接口方法，开始网络链接。
*/

class RetrofitActivity : Activity() {

    private val binding by lazy {
        ActivityRetrofitBinding.inflate(layoutInflater)
    }

    private val mRetrofit by lazy {
        Retrofit.Builder() // 增加代码量来提高可维护性
            .baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        get()

        post()
    }

    private fun post() {

        //Unable to create converter for class okhttp3.Request

        val retrofit = Retrofit.Builder() // 增加代码量来提高可维护性
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WxService::class.java)


        val requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charst=utf-8"), "Hello gorden")

        val postMarkdown = service.postMarkdown(requestBody)

        postMarkdown.enqueue(object : Callback<okhttp3.ResponseBody> {
            override fun onResponse(call: Call<okhttp3.ResponseBody>, response: Response<okhttp3.ResponseBody>) {
                Log.i("GG", """okHttpPost enqueue: onResponse:${response.toString()}}""")
            }

            override fun onFailure(call: Call<okhttp3.ResponseBody>, t: Throwable) {
            }


        })
    }

    private fun get() {
        val retrofit = Retrofit.Builder() // 增加代码量来提高可维护性
            .baseUrl("https://wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WxService::class.java)
        val articlesCall = service.getArticles("408", "1")

        //异步请求方式
        articlesCall.enqueue(object : Callback<RootBean> {
            override fun onFailure(call: Call<RootBean>, t: Throwable) {
                //请求失败
                t.printStackTrace()
                Log.e("GG", "请求失败")
            }

            override fun onResponse(call: Call<RootBean>, response: Response<RootBean>) {
                //请求成功
                val baseData = response.body()
                Log.e("GG", "请求成功 ${baseData?.toString()}")
                binding.retrofitResponse.text = baseData?.toString()
            }
        })
    }


}