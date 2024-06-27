import kotlin.system.exitProcess

fun main() {
    espaco()
    var preso : Boolean = true
    println("""
+-------------------------------+
|   1 -> Combate                |
|   2 -> Transporte             |
|   3 -> Especiais              |
|                               |
|   4 -> Armamento              |
|   5 -> Nações                 |
+-------------------------------+
|   0 -> Sair                   |
+-------------------------------+
|
|
    """.trimIndent())
    while (preso == true) {

        print("|   Opção: ")
        var opcao : String = readLine().toString()
        when (opcao) {
            "1" -> {
                preso = false
                combate()
            }

            "2" -> {
                preso = false
                transporte()
            }

            "3" -> {
                preso = false
                especiais()
            }

            "4" -> {
                preso = false
                armamento()
            }

            "5" -> {
                preso = false
                nacoes()
            }

            "0" -> exitProcess(1)

            else -> println("||| Opção desconhecida.")
        }
    }
}
