package com.example.butler.REST_requests;


import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by carlosdamasio on 14/03/17.
 */

public class requestREST {

    public static String doGET(URL url, String token) throws IOException {

        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);

            connection.setRequestProperty("tokenId", token);

            // Open communications link (network traffic occurs here).
            connection.connect();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //throw new IOException("HTTP error code: " + responseCode);
                stream = connection.getErrorStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1024.
                    result = readStream(stream, contentLength);
                } else {
                    result = null;
                }
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    public static String doDELETE(URL url, String token) throws IOException {

        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("DELETE");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);

            connection.setRequestProperty("Authorization", token);


            // Open communications link (network traffic occurs here).
            connection.connect();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //throw new IOException("HTTP error code: " + responseCode);
                stream = connection.getErrorStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1024.
                    result = readStream(stream, contentLength);
                } else {
                    result = null;
                }
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;

    }


    //REGISTER POST
    public static String doPOST(URL url, JSONObject data) throws IOException {

        InputStream stream = null;
        OutputStream out = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("POST");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            // Open communications link (network traffic occurs here).
            out = new BufferedOutputStream(connection.getOutputStream());
            out.write(data.toString().getBytes());
            out.flush();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //login failed, only method that returns 400
                if(responseCode == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    result = "login failed";
                } else {
                    stream = connection.getErrorStream();
                    if (stream != null) {
                        // Converts Stream to String with max length of 1K.
                        result = readStream(stream, contentLength);
                    }
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                } else {
                    //success with 200 ok, no body
                    result = null;
                }
            }
        } finally {
            // Close streams and disconnect HTTP connection.
            if (out != null) out.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
        return result;

    }

    //OTHER POSTS
    public static String doPOST(URL url,JSONObject data, String token) throws IOException {

        InputStream stream = null;
        OutputStream out = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("POST");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setRequestProperty("tokenId", token);

            // Open communications link (network traffic occurs here).
            out = new BufferedOutputStream(connection.getOutputStream());
            out.write(data.toString().getBytes());
            out.flush();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //login failed, only method that returns 400
                if(responseCode == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    result = "login failed";
                } else {
                    stream = connection.getErrorStream();
                    if (stream != null) {
                        // Converts Stream to String with max length of 1K.
                        result = readStream(stream, contentLength);
                    }
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                } else {
                    //success with 200 ok, no body
                    result = null;
                }
            }
        } finally {
            // Close streams and disconnect HTTP connection.
            if (out != null) out.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
        return result;
    }
    public static String doPOST(URL url,String token) throws IOException {

        InputStream stream = null;
        OutputStream out = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("POST");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setRequestProperty("tokenId", token);

            // Open communications link (network traffic occurs here).
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //login failed, only method that returns 400
                if(responseCode == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    result = "login failed";
                } else {
                    stream = connection.getErrorStream();
                    if (stream != null) {
                        // Converts Stream to String with max length of 1K.
                        result = readStream(stream, contentLength);
                    }
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                } else {
                    //success with 200 ok, no body
                    result = null;
                }
            }
        } finally {
            // Close streams and disconnect HTTP connection.
            if (out != null) out.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
        return result;
    }

    public static String doPhotoPOST(URL url, InputStream imageStream) throws IOException {

        InputStream stream = null;
        OutputStream out = null;
        HttpURLConnection connection = null;
        String result = null;

        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("POST");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Content-type", "text/plain");

            // Open communications link (network traffic occurs here).
            out = new DataOutputStream(connection.getOutputStream());
            out.write(imageStream.toString().getBytes());
            out.flush();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //login failed, only method that returns 400
                if(responseCode == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    result = "login failed";
                } else {
                    stream = connection.getErrorStream();
                    if (stream != null) {
                        // Converts Stream to String with max length of 1K.
                        result = readStream(stream, contentLength);
                    }
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                } else {
                    //success with 200 ok, no body
                    result = null;
                }
            }
        } finally {
            // Close streams and disconnect HTTP connection.
            if (out != null) out.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
        return result;
    }

    public static String doPUT(URL url, JSONObject data, String token) throws IOException {

        InputStream stream = null;
        OutputStream out = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 10000ms.
            connection.setReadTimeout(10000);
            // Timeout for connection.connect() arbitrarily set to 10000ms.
            connection.setConnectTimeout(10000);
            // For this use case, set HTTP method to PUT.
            connection.setRequestMethod("PUT");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-type", "application/json");

            connection.setRequestProperty("Authorization", token);


            // Open communications link (network traffic occurs here).
            out = new BufferedOutputStream(connection.getOutputStream());
            out.write(data.toString().getBytes());
            out.flush();
            int responseCode = connection.getResponseCode();
            int contentLength = connection.getContentLength();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                //throw new IOException("HTTP error code: " + responseCode);
                stream = connection.getErrorStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                }
            } else {
                // Retrieve the response body as an InputStream.
                stream = connection.getInputStream();
                if (stream != null) {
                    // Converts Stream to String with max length of 1K.
                    result = readStream(stream, contentLength);
                } else {
                    result = null;
                }
            }
        } finally {
            // Close streams and disconnect HTTP connection.
            if (out != null) out.close();
            if (stream != null) stream.close();
            if (connection != null) connection.disconnect();
        }
        return result;

    }

    private static String readStream(InputStream stream, int maxLength) throws IOException {
        String result = null;
        // Read InputStream using the UTF-8 charset.
        InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
        // Create temporary buffer to hold Stream data with specified max length.
        char[] buffer = new char[maxLength];
        // Populate temporary buffer with Stream data.
        int numChars = 0;
        int readSize = 0;
        while (numChars < maxLength && readSize != -1) {
            numChars += readSize;
            int pct = (100 * numChars) / maxLength;
            readSize = reader.read(buffer, numChars, buffer.length - numChars);
        }
        if (numChars != -1) {
            // The stream was not empty.
            // Create String that is actual length of response body if actual length was less than
            // max length.
            numChars = Math.min(numChars, maxLength);
            result = new String(buffer, 0, numChars);
        }
        return result;
    }

}