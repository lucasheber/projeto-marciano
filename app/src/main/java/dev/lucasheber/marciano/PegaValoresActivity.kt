package dev.lucasheber.marciano

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import dev.lucasheber.marciano.models.MarcianoAvancado
import java.util.*

class PegaValoresActivity : AppCompatActivity(R.layout.activity_pega_valores) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = findViewById<Button>(R.id.btnValor)
        val primeiroValor = findViewById<EditText>(R.id.primeiroValor)
        val segundoValor = findViewById<EditText>(R.id.segundoValor)

        val message = intent.getStringExtra("message")

        if (message != null) {
            button.text = message.uppercase()
        }

        button.setOnClickListener(View.OnClickListener {
            if (message != null) {
                val a = primeiroValor.text.toString().toDouble()
                val b = segundoValor.text.toString().toDouble()
                analisaMensagem(message, a, b)
            }
        })
    }

    private fun analisaMensagem(message: String, a: Double, b: Double)
    {
        val marciano = MarcianoAvancado()
        intent = Intent(this, AnswerAcktivity::class.java).apply {
            putExtra("answer", marciano.responda(message, a, b))
        }

        startActivity(intent)
    }
}