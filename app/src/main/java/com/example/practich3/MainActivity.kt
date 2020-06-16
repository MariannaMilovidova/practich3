package com.example.practich3

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var math: MathEval = MathEval()
    fun Factorial(num: Int): Double {
        if (num > 1000) return Double.POSITIVE_INFINITY
        if (num < 2) return Double.NaN
        var factorial = 1.0
        for (i in 2..num) {
            factorial *= i
        }
        return factorial
    }
    fun onDigitClick(view: View) {
        view as Button
        if (data.text != "") {
            if (view.text.isDigitsOnly())
                data.text = data.text.toString() + view.text.toString()
            else {
                if (data.text[data.length() - 1].isDigit())
                    data.text = data.text.toString() + view.text.toString()
                else {
                    data.text = data.text.substring(0, data.text.length - 1) + view.text
                }
            }
        }
        else {
            if(view.text.isDigitsOnly())
            data.text = data.text.toString() + view.text.toString()
        }

    }

    fun onZClick(view: View) {
        view as Button

        if (data.text != "") {
            var i= data.text.length-1
            var pos=0
            while(data.text[i].isDigit()||data.text[i]=='.')
            {
                if(data.text[i]=='.') pos++
                if(i>0) i--
                else break
            }
            if(pos==0 && data.text[data.text.length-1].isDigit())
                data.text = data.text.toString() + '.'
        } else {
            if (data.text == "") {
                data.text = "0."
            }
        }
    }

    fun onFClick(view: View) {
        view as Button
        try {
            val result: Double = math.evaluate(data.text.toString())
            data.text = Factorial(result.toInt()).toString()
        } catch (e: Exception) {
        }
    }
    fun onSqrtClick(view: View) {
        view as Button
        try {
            val result: Double = math.evaluate(data.text.toString())
            data.text = sqrt(result.toDouble()).toString()
        } catch (e: Exception) {
        }
    }
    fun onCClick(view: View) {
        data.text = ""
    }
    fun onCEClick(view: View) {
        if (data.text.isEmpty()) return
        data.text = data.text.toString().substring(0, data.text.toString().length - 1)
    }

    fun onPlusMinClick(view: View) {
        try {if(data.text!="")
            data.text = "-(${data.text})"
        } catch (e: Exception) {
        }
    }

    fun onEqClick(view: View) {

        try {
            val result: Double = math.evaluate(data.text.toString())
            data.text = result.toString()
        } catch (e: Exception) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        if(outState!=null)
        outState.run {
            putString("Key",data.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        data.text = savedInstanceState.getString("Key")
    }






}