package pe.edu.cibertec.app_fundamentos_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.app_fundamentos_kotlin.databinding.ActivityPregunta3Binding
import java.util.Locale

class Pregunta3Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPregunta3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularDesperdicio()
        }
    }

    private fun calcularDesperdicio() {
        val input = binding.etGramos.text.toString().trim()
        if (input.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la cantidad de gramos sobrantes", Toast.LENGTH_SHORT).show()
            return
        }

        val gramos = input.toDoubleOrNull()
        if ((gramos == null) || (gramos < 0)) {
            Toast.makeText(this, "Ingrese una cantidad válida de gramos", Toast.LENGTH_SHORT).show()
            return
        }

        if (gramos <= 100.0) {
            binding.tvResultado.text = "Plato dentro del margen admisible de consumo."
        } else {
            val excesoDesperdicio = gramos - 100.0
            val penalizacionTotal = 15.0 + (excesoDesperdicio * 0.12)

            binding.tvResultado.text = """
                Gramos sobrantes pesados: ${String.format(Locale.US, "%.1f", gramos)} g
                Exceso de desperdicio: ${String.format(Locale.US, "%.1f", excesoDesperdicio)} g
                Penalización total por desperdicio: S/ ${String.format(Locale.US, "%.2f", penalizacionTotal)}
            """.trimIndent()
        }
    }
}
