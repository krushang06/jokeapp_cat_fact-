package com.example.jokeapp

import com.shakebugs.shake.internal.domain.models.NetworkRequest
import java.util.*

class NRB {

    private var url: String = ""
    private var method: String = ""
    private var requestBody: String = ""
    private var timestamp: Date = Date()
    private var requestHeaders: Map<String, String> = emptyMap()
    private var responseHeaders: Map<String, String> = emptyMap()


    fun setUrl(url: String): NRB {
        this.url = url
        return this
    }

    fun setMethod(method: String): NRB {
        this.method = method
        return this
    }

    fun setRequestBody(requestBody: String): NRB {
        this.requestBody = requestBody
        return this
    }

    fun setTimestamp(timestamp: Date): NRB {
        this.timestamp = timestamp
        return this
    }

    fun setRequestHeaders(headers: Map<String, String>): NRB {
        this.requestHeaders = headers
        return this
    }

    fun setResponseHeaders(headers: Map<String, String>): NRB {
        this.responseHeaders = headers
        return this
    }

    private fun NetworkRequest(url: String, method: String, requestBody: String, timestamp: Date, requestHeaders: Map<String, String>, responseHeaders: Map<String, String>): NetworkRequest {
        return NetworkRequest(url, method, requestBody, timestamp, requestHeaders, responseHeaders)
    }

    fun build(): NetworkRequest {
        return NetworkRequest(
            url,
            method,
            requestBody,
            timestamp,
            requestHeaders,
            responseHeaders,
        )
    }

}
