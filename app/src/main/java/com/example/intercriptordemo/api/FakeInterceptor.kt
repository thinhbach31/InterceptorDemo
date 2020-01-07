package com.example.intercriptordemo.api

import com.example.intercriptordemo.BuildConfig
import com.example.intercriptordemo.model.Wallet
import okhttp3.*

class FakeInterceptor : Interceptor {
//    private val TAG = FakeInterceptor::class.simpleName

    private val wallet1 = Wallet(1, 10000)
    private val wallet2 = Wallet(2, 20000)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        if (BuildConfig.DEBUG) {
            var responseString: String? = null
            val uri = chain.request().url()
            val query = uri.query()
            val parseQuery: List<String>? = query?.split("=")

            if (parseQuery?.get(0).equals("id") && parseQuery?.get(1).equals("1")) {
                responseString = wallet1.id.toString()
            } else if (parseQuery?.get(0).equals("id") && parseQuery?.get(1).equals("2")) {
                responseString = wallet2.id.toString()
            } else {
                responseString = ""
            }

            response = Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                        responseString.toByteArray()
                    )
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            response = chain.proceed(chain.request())
        }
        return response!!
    }
}