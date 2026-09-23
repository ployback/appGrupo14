package pe.edu.cibertec.app_fundamentos_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.app_fundamentos_kotlin.databinding.ActivityPregunta2Binding
import java.util.Locale

class Pregunta2Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularExcesoPeso()
        }
    }

    private fun calcularExcesoPeso() {
        val input = binding.etPeso.text.toString().trim()
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el peso de la mascota", Toast.LENGTH_SHORT).show()
            return
        }

        val peso = input.toDoubleOrNull()
        if ((peso == null) || (peso < 0)) {
            Toast.makeText(this, "Ingrese un peso válido", Toast.LENGTH_SHORT).show()
            return
        }

        if (peso <= 8.0) {
            binding.tvResultado.text = "Mascota apta para viajar en cabina sin sobrecosto."
        } else {
            val excesoPeso = peso - 8.0
            val montoTotal = 150.0 + (excesoPeso * 35.0)

            binding.tvResultado.text = """
                Peso total ingresado: ${String.format(Locale.US, "%.2f", peso)} kg
                Exceso de peso: ${String.format(Locale.US, "%.2f", excesoPeso)} kg
                Monto total a pagar por recargo: S/ ${String.format(Locale.US, "%.2f", montoTotal)}
            """.trimIndent()
        }
    }
}
