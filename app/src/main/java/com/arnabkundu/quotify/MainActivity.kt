package com.arnabkundu.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val quoteText:TextView
        get() = findViewById(R.id.quoteText)
    private val quoteAuthor:TextView
        get() = findViewById(R.id.quoteAuthor)
    private val preBtn:TextView
        get() = findViewById(R.id.preBtn)
    private val nxtBtn:TextView
        get() = findViewById(R.id.nxtBtn)
    private val floatingActionButton:FloatingActionButton
        get() = findViewById(R.id.floatingActionButton)

    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myViewModel = ViewModelProvider(this,MyViewModelFactory(application))
            .get(MyViewModel::class.java)

        setQuote(myViewModel.getQuote())

        preBtn.setOnClickListener {
            preQuote()
        }
        nxtBtn.setOnClickListener {
            nextQuote()
        }
        floatingActionButton.setOnClickListener {
            shareQuote(myViewModel.getQuote())
        }
    }

    private fun setQuote(quote:Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    private fun nextQuote(){
        val qt = myViewModel.nextQuote()
        if(qt!=null){
            setQuote(qt)
        }
    }

    private fun preQuote(){
        val qt = myViewModel.previousQuote()
        if(qt!=null){
            setQuote(qt)
        }
    }

    private fun shareQuote(quote:Quote){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,quote.text)
        startActivity(intent)
    }
}