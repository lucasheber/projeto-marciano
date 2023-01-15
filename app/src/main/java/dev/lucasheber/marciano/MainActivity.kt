package dev.lucasheber.marciano

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dev.lucasheber.marciano.models.MarcianoAvancado

class MainActivity : AppCompatActivity() {

    private lateinit var inputText: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputText = findViewById<EditText>(R.id.editText)
        btnEnviar = findViewById<Button>(R.id.btnEnviar)

        inputText.requestFocus()
        btnEnviar.setOnClickListener(View.OnClickListener {
            analisaMensagem(inputText.text.toString())
        })
    }

    private fun analisaMensagem(message: String) {
        val marciano = MarcianoAvancado()
        val list = listOf<String>("subtraia", "some", "multiplique", "divida")
        val intent: Intent

        if (list.contains(message)) {
            intent = Intent(this, PegaValoresActivity::class.java).apply {
                putExtra("message", message)
            }
        } else {
            intent = Intent(this, AnswerAcktivity::class.java).apply {
                putExtra("answer", marciano.responda(message))
            }
        }

        startActivity(intent)
    }

    override fun onResume() {
        //Log.v("RESUME", "asdhfasdf")
        super.onResume()
        inputText.text.clear()
    }
}