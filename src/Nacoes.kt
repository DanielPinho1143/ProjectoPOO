fun addNacao() {

    titulo("Adicionar Nação")

    val codigo : Any? = valorFixo(2,"String","Código")
    val nome : Any? = valorFlexivel("String","Nome")
    val continente : Any? = valorOpcao("Continente","continente",0)

    val nacao : Nacao = Nacao("$codigo","$nome","$continente")
    adicionarFicheiro("nacoes","${nacao.codigo},${nacao.nome},${nacao.continente}")
    println("||| Submissão feita com sucesso.")
    nacoes()
}

fun detalhesNacao(escolhido: String) {
    espaco()
    var preso : Boolean = true
    var nacao = ""
    var opcao = ""
    titulo("Detalhes Nação")
    var nacoes = lerFicheiro("nacoes")
    for (row in nacoes) {
        if (separadorFicheiro(row)[1] == escolhido) {
            nacao = row
        }
    }
    var detalhes = separadorFicheiro(nacao)
    println("""
|   Código: ${detalhes[0]}
|   Nome: ${detalhes[1]}
|   Continente: ${detalhes[2]}
|
+-------------------------------+
|   0 -> Voltar                 |
|   ! -> Remover                |
+-------------------------------+
|
|
    """.trimIndent())
    while (preso == true) {
        print("|   Opção: ")
        opcao = readLine().toString()
        when (opcao) {
            "0" -> {preso = false
            nacoes()}
            "!" -> {preso = false
            removerFicheiro("nacoes","${detalhes[0]}")
            nacoes()}
            else -> println("||| Opção desconhecida.")
        }
    }

}

fun nacoes() {
    espaco()
    var preso : Boolean = true
    titulo("Nação")
    var nacoes = lerFicheiro("nacoes")
    if (nacoes.count() == 0) {
        println("|   Não existe nações.")
    }
    var i : Int = 0
    for (row in nacoes) {
        i++
        println("|   $i -> ${separadorFicheiro(row)[1]}")
    }
    println("""
|
+-------------------------------+
|   0 -> Voltar                 |
|   + -> Adicionar              |
+-------------------------------+
|
|
    """.trimIndent())
    while (preso == true) {

        print("|   Opção: ")
        var opcao : String = readLine().toString()
        when (opcao) {
            "0" -> {preso = false
            main()}
            "+" -> {preso = false
            addNacao()}
            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesNacao("${separadorFicheiro(nacoes[opcao-1])[1]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}