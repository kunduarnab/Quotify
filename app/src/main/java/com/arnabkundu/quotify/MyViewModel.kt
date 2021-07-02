package com.arnabkundu.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream

class MyViewModel(val context: Context) : ViewModel() {
    private var quoteList:Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        val inputStream: InputStream = context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() : Quote? {
        if(index<=quoteList.size){
            return quoteList[++index]
        }
        return null
    }

    fun previousQuote() : Quote?{
        if(index>0){
            return quoteList[--index]
        }
        return null
    }
}