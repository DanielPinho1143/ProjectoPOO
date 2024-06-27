fun addEspecial() {

    titulo("Adicionar Veículo Especial")

    var nome : Any? = valorFlexivel("String","Nome")
    var tipo : Any? = valorOpcaoCamadas("Tipo","tipo",1,"transporte")
    var servico : Any? = valorFixo(4,"Int","Servico")
    var origem : Any? = valorOpcao("Origem","nacoes",1)
    var mobilidade : Any? = valorOpcao("Mobilidade","mobilidade",0)
    var variante : Any? = valorOpcaoCamadas("Variante","mobilidade",1,"$mobilidade")
    var motor : Any? = valorFlexivel("String","Motor")
    var peso : Any? = valorFlexivel("Int","Peso")
    var velocidade : Any? = valorFlexivel("Int","Velocidade")

    var armamento : Any? = valorOpcao("Suporte de Armamento","truefalse",0)
    var tripulacao : Any? = valorFlexivel("Int","Tripulação")
    var capacidade : Any? = valorFlexivel("Int","Capacidade")
    var motivo : Any? = valorFlexivel("String","Motivo")

    val veiculo : Especial = Especial(
        "$nome",
        "$tipo",
        "$servico".toInt(),
        "$origem",
        "$mobilidade",
        "$variante",
        "$motor",
        "$peso".toInt(),
        "$velocidade".toInt(),
        "$armamento".toBoolean(),
        "$tripulacao".toInt(),
        "$capacidade".toInt(),
        "$motivo",
    )

    adicionarFicheiro("especiais","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.armamento},${veiculo.tripulacao},${veiculo.capacidade},${veiculo.motivo}")

    println("||| Submissão feita com sucesso.")
    especiais()
}

fun detalhesEspecial(escolhido: String) {
    espaco()
    var preso : Boolean = true
    var veiculo = ""
    var opcao = ""

    titulo("Detalhes Especiais")

    var ficheiro = lerFicheiro("especiais")
    for (row in ficheiro) {
        if (separadorFicheiro(row)[0] == escolhido) {
            veiculo = row
        }
    }

    var detalhes = separadorFicheiro(veiculo)

    println("""
|   Nome: ${detalhes[0]}
|   Tipo: ${detalhes[1]}
|   Servico: ${detalhes[2]}
${separador()}
|   Mobilidade: ${detalhes[4]} (${detalhes[5]})
${separador()}
|   Motor: ${detalhes[6]}
|   Peso: ${detalhes[7]}
|   Velocidade: ${detalhes[8]}
${separador()}
|   Armamento: ${detalhes[9]}
|   Tripulação: ${detalhes[10]}
|   Capacidade: ${detalhes[11]}
${separador()}
|   Motivo: ${detalhes[12]}
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
                especiais()}
            "!" -> {preso = false
                removerFicheiro("especiais","${detalhes[0]}")
                especiais()}
            else -> println("||| Opção desconhecida.")
        }
    }

}

fun especiaisFiltro(filtro: String) {
    var alvo = 1
    var lista : MutableList<String> = mutableListOf()

    espaco()
    var preso : Boolean = true
    titulo("Veículos Especiais")
    var veiculo = lerFicheiro("especiais")
    for (row in veiculo) {
        var rowSeparado = separadorFicheiro(row)
        if (rowSeparado[alvo] == filtro) {
            lista.add(row)
        }
    }
    if (lista.count() == 0) {
        println("|   Não existe veículos especiais com base neste filtro.")
    }
    var i : Int = 0
    for (row in lista) {
        i++
        println("|   $i -> ${separadorFicheiro(row)[0]}")
    }
    println("""
|
+-------------------------------+
|   0 -> Voltar                 |
|   + -> Adicionar              |
|   ? -> Filtro                 |
+-------------------------------+
|
|
    """.trimIndent())
    while (preso == true) {

        print("|   Opção: ")
        var opcao : String = readLine().toString()
        when (opcao) {
            "0" -> {
                preso = false
                main()
            }

            "+" -> {
                preso = false
                addEspecial()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"especiais")
                especiaisFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesEspecial("${separadorFicheiro(lista[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}

fun especiais() {
    espaco()
    var preso : Boolean = true
    titulo("Veículos Especiais")
    var especiais = lerFicheiro("especiais")
    if (especiais.count() == 0) {
        println("|   Não existe veículos especiais.")
    }
    var i : Int = 0
    for (row in especiais) {
        i++
        println("|   $i -> ${separadorFicheiro(row)[0]}")
    }
    println("""
|
+-------------------------------+
|   0 -> Voltar                 |
|   + -> Adicionar              |
|   ? -> Filtro                 |
+-------------------------------+
|
|
    """.trimIndent())
    while (preso == true) {

        print("|   Opção: ")
        var opcao : String = readLine().toString()
        when (opcao) {
            "0" -> {
                preso = false
                main()
            }

            "+" -> {
                preso = false
                addEspecial()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"especiais")
                especiaisFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesEspecial("${separadorFicheiro(especiais[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}