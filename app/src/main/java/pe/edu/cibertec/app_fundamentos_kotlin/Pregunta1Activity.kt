package pe.edu.cibertec.app_fundamentos_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.app_fundamentos_kotlin.databinding.ActivityPregunta1Binding
import java.util.Locale

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularLateCheckout()
        }
    }

    private fun calcularLateCheckout() {
        val input = binding.etHoras.text.toString().trim()
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese las horas adicionales", Toast.LENGTH_SHORT).show()
            return
        }

        val horas = input.toDoubleOrNull()
        if ((horas == null) || (horas < 0)) {
            Toast.makeText(this, "Ingrese un número válido de horas", Toast.LENGTH_SHORT).show()
            return
        }

        if (horas <= 2) {
            binding.tvResultado.text = "Salida dentro del margen de cortesía del hotel."
        } else {
            val horasSujetas = horas - 2
            val cargoTotal = 60.0 + (horasSujetas * 25.0)

            binding.tvResultado.text = """
                Horas adicionales solicitadas: $horas horas
                Horas sujetas a cobro: $horasSujetas horas
                Cargo total por Late Check-out: S/ ${String.format(Locale.US, "%.2f", cargoTotal)}
            """.trimIndent()
        }
    }
}
