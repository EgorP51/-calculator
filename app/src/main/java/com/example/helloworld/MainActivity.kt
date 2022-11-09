package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

import android.util.Log
import android.view.LayoutInflater
import com.example.helloworld.databinding.ActivityMainBinding
import com.notkamui.keval.Keval
import com.notkamui.keval.KevalZeroDivisionException
import kotlin.math.min
import kotlin.math.roundToInt

//import kotlinx.android.synthetic.

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.btn0.setOnClickListener { setTextField("0") }
        bindingClass.btn1.setOnClickListener { setTextField("1") }
        bindingClass.btn2.setOnClickListener { setTextField("2") }
        bindingClass.btn3.setOnClickListener { setTextField("3") }
        bindingClass.btn4.setOnClickListener { setTextField("4") }
        bindingClass.btn5.setOnClickListener { setTextField("5") }
        bindingClass.btn6.setOnClickListener { setTextField("6") }
        bindingClass.btn7.setOnClickListener { setTextField("7") }
        bindingClass.btn8.setOnClickListener { setTextField("8") }
        bindingClass.btn9.setOnClickListener { setTextField("9") }
        bindingClass.addBtn.setOnClickListener { setTextField("+") }
        bindingClass.minBtn.setOnClickListener { setTextField("-") }
        bindingClass.multBtn.setOnClickListener { setTextField("*") }
        bindingClass.slashBtn.setOnClickListener { setTextField("/") }
        bindingClass.dotBtn.setOnClickListener { setTextField(".") }
        bindingClass.lBtn.setOnClickListener { setTextField("(") }
        bindingClass.rBtn.setOnClickListener { setTextField(")") }


        bindingClass.acBtn.setOnClickListener {
            if(bindingClass.mathOperation.text.isNotEmpty()){
                bindingClass.mathOperation.text = ""
                bindingClass.resultText.text = ""
            }
        }
        bindingClass.backBtn.setOnClickListener {
            if(bindingClass.mathOperation.text.length > 0){
                val str = bindingClass.mathOperation.text
                bindingClass.mathOperation.text = str.substring(0,str.length - 1)
            }
        }

        bindingClass.equalBtn.setOnClickListener {
            try{
                //val roundoff = (random * 10000.0).roundToInt() / 10000.0

                val result = (Keval.eval(bindingClass.mathOperation.text.toString())*10000.0).roundToInt() / 10000.0

                var longResult = result.toLong()

                if(result == longResult.toDouble()){
                    bindingClass.resultText.text = longResult.toString()
                }else{
                    bindingClass.resultText.text = result.toString()
                }
            }catch (ex: KevalZeroDivisionException){
                bindingClass.resultText.text = "error: x/0"
            }
            catch (e: Exception){
                bindingClass.resultText.text = "some error"
            }
        }
    }

    fun setTextField(str: String){
        if(bindingClass.resultText.text != ""){
            bindingClass.mathOperation.text = bindingClass.resultText.text
            bindingClass.resultText.text = ""
        }
        bindingClass.mathOperation.append(str)
    }

}