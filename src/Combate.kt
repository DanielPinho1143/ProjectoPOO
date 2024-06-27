fun addCombate() {

    titulo("Adicionar Combate")

    var nome : Any? = valorFlexivel("String","Nome")
    var tipo : Any? = valorOpcaoCamadas("Tipo","tipo",1,"combate")
    var servico : Any? = valorFixo(4,"Int","Servico")
    var origem : Any? = valorOpcao("Origem","nacoes",1)
    var mobilidade : Any? = valorOpcao("Mobilidade","mobilidade",0)
    var variante : Any? = valorOpcaoCamadas("Variante","mobilidade",1,"$mobilidade")
    var motor : Any? = valorFlexivel("String","Motor")
    var peso : Any? = valorFlexivel("Int","Peso")
    var velocidade : Any? = valorFlexivel("Int","Velocidade")

    var principal : Any? = valorOpcao("Armamento Principal","armamentos",0)
    var secundario : Any? = valorOpcao("Armamento Secundário","armamentos",0)
    var tripulacao : Any? = valorFlexivel("Int","Tripulação")

    when (tipo) {
        "ifv" -> {
            var capacidade : Any? = valorFlexivel("Int","Capacidade")

            val veiculo : IFV = IFV(
                "$nome",
                "$tipo",
                "$servico".toInt(),
                "$origem",
                "$mobilidade",
                "$variante",
                "$motor",
                "$peso".toInt(),
                "$velocidade".toInt(),
                "$principal",
                "$secundario",
                "$tripulacao".toInt(),
                "$capacidade".toInt(),
            )

            adicionarFicheiro("combate","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.principal},${veiculo.secundario},${veiculo.tripulacao},${veiculo.capacidade}")
        }

        "carro-combate", "artilharia-autopropulsada" -> {
            var automatico : Any? = valorOpcao("Carregamento Automático","truefalse",0)
            if (automatico == "true") {
                var modelo : Any? = valorOpcao("Modelo Automático","automatico",0)
                var tempo : Any? = valorFlexivel("Double","Tempo")

                val veiculo : Automatico = Automatico(
                    "$nome",
                    "$tipo",
                    "$servico".toInt(),
                    "$origem",
                    "$mobilidade",
                    "$variante",
                    "$motor",
                    "$peso".toInt(),
                    "$velocidade".toInt(),
                    "$principal",
                    "$secundario",
                    "$tripulacao".toInt(),
                    "$modelo",
                    "$tempo".toDouble()
                )

                adicionarFicheiro("combate","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.principal},${veiculo.secundario},${veiculo.tripulacao},${veiculo.modelo},${veiculo.tempo}")
            } else {
                val veiculo : Combate = Combate(
                    "$nome",
                    "$tipo",
                    "$servico".toInt(),
                    "$origem",
                    "$mobilidade",
                    "$variante",
                    "$motor",
                    "$peso".toInt(),
                    "$velocidade".toInt(),
                    "$principal",
                    "$secundario",
                    "$tripulacao".toInt()
                )

                adicionarFicheiro("combate","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.principal},${veiculo.secundario},${veiculo.tripulacao}")
            }
        }

        else -> {
            val veiculo : Combate = Combate(
                "$nome",
                "$tipo",
                "$servico".toInt(),
                "$origem",
                "$mobilidade",
                "$variante",
                "$motor",
                "$peso".toInt(),
                "$velocidade".toInt(),
                "$principal",
                "$secundario",
                "$tripulacao".toInt()
            )

            adicionarFicheiro("combate","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.principal},${veiculo.secundario},${veiculo.tripulacao}")
        }
    }

    println("||| Submissão feita com sucesso.")
    combate()
}

fun detalhesCombate(escolhido: String) {
    espaco()
    var preso : Boolean = true
    var veiculo = ""
    var opcao = ""

    titulo("Detalhes Combate")

    var ficheiro = lerFicheiro("combate")
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
||| Armamento Principal
${informacaoArmamento(detalhes[9])}
${separador()}
||| Armamento Secundário
${informacaoArmamento(detalhes[10])}
${separador()}
|   Tripulação: ${detalhes[11]}
${separador()}
${informacaoNacao(detalhes[3])}
${separador()}
${informacaoVeiculo(detalhes[0],"combate")}
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
                combate()}
            "!" -> {preso = false
                removerFicheiro("combate","${detalhes[0]}")
                combate()}
            else -> println("||| Opção desconhecida.")
        }
    }

}

fun combateFiltro(filtro: String) {
    var alvo = 1
    var lista : MutableList<String> = mutableListOf()

    espaco()
    var preso : Boolean = true
    titulo("Veículos de Combate")
    var veiculo = lerFicheiro("combate")
    for (row in veiculo) {
        var rowSeparado = separadorFicheiro(row)
        if (rowSeparado[alvo] == filtro) {
            lista.add(row)
        }
    }
    if (lista.count() == 0) {
        println("|   Não existe veículos de combate com base neste filtro.")
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
                addCombate()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"combate")
                combateFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesCombate("${separadorFicheiro(lista[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}

fun combate() {
    espaco()
    var preso : Boolean = true
    titulo("Veículos de Combate")
    var combate = lerFicheiro("combate")
    if (combate.count() == 0) {
        println("|   Não existe veículos de combate.")
    }
    var i : Int = 0
    for (row in combate) {
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
                addCombate()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"combate")
                combateFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesCombate("${separadorFicheiro(combate[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}