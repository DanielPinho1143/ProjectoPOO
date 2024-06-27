import java.io.File

fun lerFicheiro(caminho : String): MutableList<String> {
    val caminho : String = "dados/" + caminho + ".csv"
    var lista :MutableList<String> = mutableListOf<String>()

    File(caminho).forEachLine {
        lista.add(it)
    }

    lista.removeAt(0)
    return lista
}

fun lerFicheiroCompleto(caminho : String): MutableList<String> {
    val caminho : String = "dados/" + caminho + ".csv"
    var lista :MutableList<String> = mutableListOf<String>()

    File(caminho).forEachLine {
        lista.add(it)
    }

    return lista
}

fun separadorFicheiro(variavel: String): List<String> {
    var i : Int = 0
    var variavel : List<String> = variavel.split(",")
    return variavel
}

fun lerFiltro(filtro: String, caminho: String) {
    var i : Int = 0
    val lista : MutableList<String> = lerFicheiro(caminho)

    lista.forEach {
        var it = it.split(",")
        if (it[1] == filtro) {
            i++
            println("$i: ${it[0]} ${it[1]} ${it[2]}")
        }
    }
}

fun adicionarFicheiro(caminho: String, dados: String) {
    File("dados/$caminho.csv").appendText("$dados\n")
}

fun removerFicheiro(caminho: String, alvo: String) {
    val ficheiro = lerFicheiroCompleto(caminho)
    var lista = mutableListOf<Any?>()
    var i = 0
    for (row in ficheiro) {
        if (separadorFicheiro(row)[0] == alvo) {

        } else {
            lista.add(row)
        }
    }

    for (row in lista) {
        i++
        if (i == 1) {
            File("dados/$caminho.csv").writeText("$row\n")
        } else {
            adicionarFicheiro("$caminho","$row")
        }
    }
}