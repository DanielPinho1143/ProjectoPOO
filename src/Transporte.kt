fun addTransporte() {

    titulo("Adicionar Veículo Transporte")

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
    var capacidade : Any? = valorFlexivel("Int","Capacidade")

    when (tipo) {
        "apc" -> {
            var tripulacao : Any? = valorFlexivel("Int","Tripulação")

            val veiculo : APC = APC(
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
                "$capacidade".toInt(),
                "$tripulacao".toInt(),
            )

            adicionarFicheiro("transporte","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.armamento},${veiculo.capacidade},${veiculo.tripulacao}")
        }

        else -> {
            val veiculo : Transporte = Transporte(
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
                "$capacidade".toInt()
            )

            adicionarFicheiro("transporte","${veiculo.nome},${veiculo.tipo},${veiculo.servico},${veiculo.origem},${veiculo.mobilidade},${veiculo.variante},${veiculo.motor},${veiculo.peso},${veiculo.velocidade},${veiculo.armamento},${veiculo.capacidade}")
        }
    }

    println("||| Submissão feita com sucesso.")
    transporte()
}

fun detalhesTransporte(escolhido: String) {
    espaco()
    var preso : Boolean = true
    var veiculo = ""
    var opcao = ""

    titulo("Detalhes Transporte")

    var ficheiro = lerFicheiro("transporte")
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
|   Capacidade: ${detalhes[10]}
${separador()}
${informacaoNacao(detalhes[3])}
${separador()}
${informacaoVeiculo(detalhes[0],"transporte")}
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
                transporte()}
            "!" -> {preso = false
                removerFicheiro("transporte","${detalhes[0]}")
                transporte()}
            else -> println("||| Opção desconhecida.")
        }
    }

}

fun transporteFiltro(filtro: String) {
    var alvo = 1
    var lista : MutableList<String> = mutableListOf()

    espaco()
    var preso : Boolean = true
    titulo("Veículos de Transporte")
    var veiculo = lerFicheiro("transporte")
    for (row in veiculo) {
        var rowSeparado = separadorFicheiro(row)
        if (rowSeparado[alvo] == filtro) {
            lista.add(row)
        }
    }
    if (lista.count() == 0) {
        println("|   Não existe veículos de transporte com base neste filtro.")
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
                addTransporte()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"transporte")
                transporteFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesTransporte("${separadorFicheiro(lista[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}

fun transporte() {
    espaco()
    var preso : Boolean = true
    titulo("Veículos de Transporte")
    var transporte = lerFicheiro("transporte")
    if (transporte.count() == 0) {
        println("|   Não existe veículos de transporte.")
    }
    var i : Int = 0
    for (row in transporte) {
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
                addTransporte()
            }

            "?" -> {
                preso = false
                var filtro = valorOpcaoCamadas("Filtro","tipo",1,"transporte")
                transporteFiltro("$filtro")
            }

            else -> try {
                var opcao = opcao.toInt()
                if (opcao in 1..i) {
                    detalhesTransporte("${separadorFicheiro(transporte[opcao-1])[0]}")
                    preso = false
                }
            } catch (e: Exception) {
                "||| Opção desconhecida."
            }
        }
    }
}