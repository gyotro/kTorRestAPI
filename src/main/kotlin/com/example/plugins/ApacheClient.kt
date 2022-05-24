package com.example.plugins

import io.ktor.client.*
import io.ktor.client.engine.apache.*
import org.slf4j.event.*

class ApacheClient() {
    val client = HttpClient(Apache) {
        engine {

            // this: ApacheEngineConfig
            followRedirects = true
            socketTimeout = 10_000
            connectTimeout = 10_000
            connectionRequestTimeout = 20_000
            /*customizeClient {
                // this: HttpAsyncClientBuilder
                setProxy(HttpHost("127.0.0.1", 8080))
                setMaxConnTotal(1000)
                setMaxConnPerRoute(100)
                // ...
            }
            customizeRequest {
                // this: RequestConfig.Builder
            }*/
        }

    }

}
