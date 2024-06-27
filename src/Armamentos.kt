fun addArmamento() {

    titulo("Adicionar Armamento")

    val nome : Any? = valorFlexivel("String","Nome")
    val tipo : Any? = valorFlexivel("String","Tipo")
    var servico : Any? = valorFixo(4,"Int","Servico")
    val origem : Any? = valorOpcao("Origem","nacoes",1)
    val calibre : Any? = valorFlexivel("String","Calibre")

    val armamento : Armamento = Armamento("$nome","$tipo","$servico".toInt(),"$origem","$calibre".toDouble())
    adicionarFicheiro("armamentos","${armamento.nome},${armamento.tipo},${armamento.servico},${armamento.origem},${armamento.calibre}")
    println("||| Submissão feita com sucesso.")
    armamento()
}

fun detalhesArmamento(escolhido: String) {
    espaco()
    var preso : Boolean = true
    var armamento = ""
    var opcao = ""

    titulo("Detalhes Armamento")

    var armamentos = lerFicheiro("armamentos")
    for (row in armamentos) {
        if (separadorFicheiro(row)[0] == escolhido) {
            armamento = row
        }
    }

    var detalhes = separadorFicheiro(armamento)

    println("""
|   Nome: ${detalhes[0]}
|   Tipo: ${detalhes[1]}
|   Servico: ${detalhes[2]}
|   Calibre: ${detalhes[4]}
${separador()}
${informacaoNacao(detalhes[3])}
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
                armamento()}
            "!" -> {preso = false
                removerFicheiro("armamentos","${detalhes[0]}")
                armamento()}
            else -> println("||| Opção desconhecida.")
        }
    }

}

fun armamento() {
    espaco()
    var preso : Boolean = true
    titulo("Armamentos")
    var armamentos = lerFicheiro("armamentos")
    if (armamentos.count() == 0) {
        println("|   Não existe armamentos.")
    }
    var i : Int = 0
    for (row in armamentos) {
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
                addArmamento()}
            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesArmamento("${separadorFicheiro(armamentos[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}