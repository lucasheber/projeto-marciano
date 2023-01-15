package dev.lucasheber.marciano.models

fun interface MarcianoInterface {
    fun agir(): String
}

open class Marciano() {
    private val pergunta = "Certamente"
    private val grito = "Opa! Calma aí!"
    private val pergunta_grito = "Relaxa, eu sei o que estou fazendo!"
    private val eu = "A responsabilidade é sua"
    private val vazio = "Não me incomode"
    private val outro = "Tudo bem, como quiser"

    open fun responda(comando: String): String {
        comando.run {
            return when {
                isNullOrBlank() -> vazio
                contains("eu").or(contains("Eu").or(contains("EU"))) -> eu
                contains("?").and(split(" ").any { it.uppercase().equals(it) }) -> pergunta_grito
                split(" ").any { it.uppercase().equals(it) } -> grito
                contains("?") -> pergunta
                else -> outro
            }
        }
    }
}

open class MarcianoAvancado() : Marciano() {
    open fun responda(comando: String, oper1: Double? = null, oper2: Double? = null): String {
        if (oper1 != null && oper2 != null) {
            val res = when (comando) {
                "some" -> oper1 + oper2
                "subtraia" -> oper1 - oper2
                "multiplique" -> oper1 * oper2
                "divida" -> oper1 / oper2
                else -> "Ou não"
            }
            return "Essa eu sei. $res"
        } else return super.responda(comando)
    }
}

open class MarcianoPremium(private val acao: MarcianoInterface) : MarcianoAvancado() {
    override fun responda(comando: String): String {
        return if (comando == "agir") {
            "É pra já!\n${acao.agir()}"
        } else super.responda(comando)
    }
}