package com.example.gezegenlerdekikilom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val KILO_TO_POUND=2.2045
    val MARS=0.38
    val POUND_TO_KILO=0.45359237
    val VENUS=0.91
    val JUPYTER=2.34

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cbVenus.setOnClickListener(this)
        cbJupyter.setOnClickListener(this)
        cbMars.setOnClickListener(this)

        Glide.with(this).load(R.drawable.title).into(imageView)

        twSonuc.text=savedInstanceState?.getString("sonuc")

        /*  btnHesapla.setOnClickListener(object : View.OnClickListener  {
        override fun onClick(v: View?) {
           Log.e("Ahmet Hakan","Butona Basıldı")
        }
    })


        btnHesapla.setOnClickListener {
           var kullaniciAgirlikPound=kiloToPound(kullaniciKilo.toString().toDouble())
            var marstakiAgirlikPound=kullaniciAgirlikPound*MARS
            var marstakiAgirlikKilo=poundToKilo(marstakiAgirlikPound)

            twSonuc.text=marstakiAgirlikKilo.formatla(2).toString()
        }


*/
    }

        fun kiloToPound(kilo: Double): Double {

            return kilo * KILO_TO_POUND
        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putString("sonuc",twSonuc.text.toString())
    }

        fun poundToKilo(pound: Double): Double {

            return pound * POUND_TO_KILO
        }

        override fun onClick(v: View?) {
            v as CheckBox
            var isCheck: Boolean = v.isChecked


            if(!TextUtils.isEmpty(etKilo.text.toString())){
                var kullaniciKilo = etKilo.text.toString().toDouble()
                var kullaniciPound = kiloToPound(kullaniciKilo)

                when (v.id) {
                    R.id.cbMars -> if (isCheck) {
                        cbJupyter.isChecked=false
                        cbVenus.isChecked=false
                        hesaplaAgirlikPound(kullaniciPound, v)
                    }
                    R.id.cbJupyter -> if (isCheck) {
                        cbMars.isChecked=false
                        cbVenus.isChecked=false
                        hesaplaAgirlikPound(kullaniciPound, v)
                    }
                    R.id.cbVenus -> if (isCheck) {
                        cbJupyter.isChecked=false
                        cbMars.isChecked=false
                        hesaplaAgirlikPound(kullaniciPound, v)
                    }
                }
            }

        }

        fun hesaplaAgirlikPound(pound: Double, checkbox: CheckBox) {
            var sonuc: Double = 0.0

            when (checkbox.id) {
                R.id.cbMars -> sonuc = pound * MARS
                R.id.cbVenus -> sonuc = pound * VENUS
                R.id.cbJupyter -> sonuc = pound * JUPYTER
                else -> sonuc = 0.0
            }

            var sonucToKilo = poundToKilo(sonuc)
            twSonuc.text = "Kilonuz : " + sonucToKilo.formatla(2)
        }

        fun Double.formatla(kacTaneRakam: Int) = java.lang.String.format("%.${kacTaneRakam}f ", this)

    }

