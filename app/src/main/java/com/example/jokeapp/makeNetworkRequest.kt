package com.example.jokeapp

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun makeNetworkRequest(urlString: String, method: String = "GET", requestBody: String? = null): String {
    val url = URL(urlString)
    val connection = url.openConnection() as HttpURLConnection
    connection.doOutput = method == "POST" // Set for POST requests

    connection.requestMethod = method

    // Set headers (optional)
    connection.setRequestProperty("Content-Type", "application/json") // Example

    // Send request body (for POST)
    if (requestBody != null) {
        val outputStream = connection.outputStream
        outputStream.write(requestBody.toByteArray())
        outputStream.flush()
        outputStream.close()
    }

    // Read the response
    val responseCode = connection.responseCode
    val responseString = if (responseCode == HttpURLConnection.HTTP_OK) {
        val inputStream = connection.inputStream
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readText()
    } else {
        "Error: Response code $responseCode"
    }

    connection.disconnect()
    return responseString
}
