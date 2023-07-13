package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var oldConc: Double = 0.0
    var oldOperation:String ="null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    fun btnNumberClick(view: View) {
        val button = view as Button
        val buttonValue = button.text.toString()
       binding.conculationText.text = Editable.Factory.getInstance().newEditable(if(binding.conculationText.text.toString()=="0") buttonValue else binding.conculationText.text.toString()+buttonValue  )
    }

    fun btnFunction(view: View){
        val button = view as Button


       binding.operationView.text= if(button.id !==binding.buttonDel.id )  button.text.toString() else binding.operationView.text.toString()
        when(button.id){
            binding.buttonAC.id ->{
                binding.conculationText.text =Editable.Factory.getInstance().newEditable("0")
                oldConc = 0.0
                binding.conculationView.text = ""
                binding.operationView.text=""
            }
            binding.buttonDel.id ->{
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(binding.conculationText.text.toString().dropLast(1))

            }
            binding.buttonE.id ->{

                calculate(binding.conculationText.text.toString())
                binding.conculationView.text = ""

                oldOperation = "null"
            }
            binding.buttonP.id ->{
                binding.conculationView.text =calculate(binding.conculationText.text.toString()).toString()
                oldOperation = "sum"
                binding.conculationText.text =Editable.Factory.getInstance().newEditable("0")

            }
            binding.buttonM.id ->{

                binding.conculationView.text =calculate(binding.conculationText.text.toString()).toString()
                oldOperation = "min"
                binding.conculationText.text =Editable.Factory.getInstance().newEditable("0")

            }
            binding.buttonD.id ->{
                binding.conculationView.text =calculate(binding.conculationText.text.toString()).toString()
                oldOperation = "div"
                binding.conculationText.text =Editable.Factory.getInstance().newEditable("0")


            }
            binding.buttonX.id ->{
                binding.conculationView.text =calculate(binding.conculationText.text.toString()).toString()
                oldOperation = "mul"
                binding.conculationText.text =Editable.Factory.getInstance().newEditable("0")

            }
            binding.buttonSign.id->{
                val newValue = binding.conculationText.text.toString().toFloat() * -1
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(newValue.toString())
            }
        }

    }

    fun calculate(conc:String):Double{
        println("oldd"+ oldConc+ oldOperation+ " " +conc.toString())
        when(oldOperation){
            "sum"->{
                oldConc +=conc.toFloat()
                print("summm"+ oldConc)
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(oldConc.toString())

            }
            "min"->{
                oldConc -=conc.toFloat()
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(oldConc.toString())
            }
            "div"->{
              if (oldConc !=0.0)  oldConc /= conc.toFloat() else oldConc = conc.toDouble()
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(oldConc.toString())
            }
            "mul"->{
                if (oldConc !=0.0)  oldConc *=conc.toFloat() else oldConc = conc.toDouble()
                binding.conculationText.text = Editable.Factory.getInstance().newEditable(oldConc.toString())
            }

        }
        return oldConc
    }
}