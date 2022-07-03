package com.gorden.happycoding.retrofit

import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WxService {

    /*
    https://wanandroid.com/wxarticle/list/408/1/json
    方法：GET
    参数：
	公众号 ID：拼接在 url 中，eg:405
	公众号页码：拼接在url 中，eg:1
     */

    @GET("wxarticle/list/{id}/{page}/json")
    fun getArticles(@Path("id") id:String, @Path("page") page:String) : Call<RootBean>


    @POST("markdown/raw")
    fun postMarkdown(@Body requestBody: RequestBody): Call<ResponseBody>

}