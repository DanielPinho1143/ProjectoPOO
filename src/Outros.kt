fun separador() : String {
    val valor = "|\n|- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---"
    return valor
}

fun espaco() {
    println("""
        
        
        
        
        
    """.trimIndent())
}

fun titulo(titulo: String) {
    val suporte : String = "                                                           |"
    println("+--------------------------------------------------------------+")
    println("|                                                              |")
    println("|   ${titulo}")
    println("|                                                              |")
    println("+--------------------------------------------------------------+")
    println("|")
}

fun leitura(tipo: String, questao: String) : Any? {
    var preso: Boolean = true
    var valor = 0
    while (preso == true) {
        when (tipo) {
            "String" -> try {
                print("|   $questao ($tipo): ")
                val valor: String = readLine().toString()
                preso = false
                return valor
            } catch (e: Exception) {
                println("||| Erro. $e")
            }
            "Int" -> try {
                print("|   $questao ($tipo): ")
                val valor: Int = readLine()!!.toInt()
                preso = false
                return valor
            } catch (e: Exception) {
                println("||| Erro. $e")
            }
            "Double" -> try {
                print("|   $questao ($tipo): ")
                val valor: Double = readLine()!!.toDouble()
                preso = false
                return valor
            } catch (e: Exception) {
                println("||| Erro. $e")
            }
            "Boolean" -> try {
                print("|   $questao ($tipo): ")
                val valor: Boolean = readLine().toBoolean()
                preso = false
                return valor
            } catch (e: Exception) {
                println("||| Erro. $e")
            }
        }
    }
    return valor
}

fun valorFixo (alvo : Int, tipo : String, questao : String) : Any? {
    var preso : Boolean = true
    var valor = ""
    while (preso == true) {
        valor = leitura("$tipo","$questao").toString()
        if (valor.toString().length == alvo) {
            preso = false
        } else {
            println("||| Erro. O valor tem de ter apenas $alvo caracteres.")
        }
    }
    return valor
}

fun valorFlexivel (tipo : String, questao : String) : Any? {
    var preso : Boolean = true
    var valor = ""
    while (preso == true) {
        valor = leitura("$tipo","$questao").toString()
        if (valor.toString().length == 0) {
            println("||| Erro. Convém o valor ter algum conteúdo.")
        } else {
            preso = false
        }
    }
    return valor
}

fun valorOpcao (questao : String, ficheiro : String, alvo : Int) : Any? {
    var preso : Boolean = true
    var lista1 : MutableList<String> = mutableListOf()
    var lista2 : MutableList<String> = mutableListOf()
    var linha = ""

    var ficheiro = lerFicheiro("$ficheiro")
    var i : Int = 0

    for (row in ficheiro) {
        var rowSeparado = separadorFicheiro(row)
        if (rowSeparado[0] !in lista1) {
            lista1.add(rowSeparado[0])
            lista2.add(row)
        }
    }

    for (row in lista2) {
        i++
        println("|   $i -> ${separadorFicheiro(row)[alvo]}")
    }
    while (preso == true) {
        try {
            print("|   $questao (Opção): ")
            var opcao : Int = readLine()!!.toInt()
            if (opcao in 1..i) {
                linha = separadorFicheiro(ficheiro[opcao-1])[0]
                preso = false
            } else {
                println("||| Erro. Escolha uma opção disponível.")
            }
        } catch (e: Exception) {
            println("||| Erro. $e")
        }
    }
    return linha
}

fun valorOpcaoCamadas (questao : String, ficheiro : String, alvo : Int, ramo : String) : Any? {
    var preso : Boolean = true
    var linha = ""
    var lista : MutableList<String> = mutableListOf()

    var ficheiro = lerFicheiro("$ficheiro")
    for (row in ficheiro) {
        if (separadorFicheiro(row)[0] == ramo) {
            lista.add(row)
        }
    }

    var i : Int = 0
    for (row in lista) {
        i++
        println("|   $i -> ${separadorFicheiro(row)[alvo]}")
    }
    while (preso == true) {
        try {
            print("|   $questao (Opção): ")
            var opcao : Int = readLine()!!.toInt()
            if (opcao in 1..i) {
                linha = separadorFicheiro(lista[opcao-1])[alvo]
                preso = false
            } else {
                println("||| Erro. Escolha uma opção disponível.")
            }
        } catch (e: Exception) {
            println("||| Erro. $e")
        }
    }
    return linha
}

fun informacaoVeiculo (alvo: String, ficheiro: String) : String {
    var veiculo = ""
    var valor : String = ""

    try {
        var ficheiro = lerFicheiro("$ficheiro")

        for (row in ficheiro) {
            if (separadorFicheiro(row)[0] == alvo) {
                veiculo = row
            }
        }
        var detalhesVeiculo = separadorFicheiro(veiculo)

        when (detalhesVeiculo[1]) {
            "ifv" -> {
                valor = ("""
|   Capacidade: ${detalhesVeiculo[12]}
                """.trimIndent())
            }

            "carro-combate", "artilharia-autopropulsada" -> {
                try {
                    valor = ("""
|   Carregamento Automático: ${detalhesVeiculo[12]}
|   Velocidade: ${detalhesVeiculo[13]}
                """.trimIndent())
                } catch (e: Exception) {
                    valor = ("""
|   Carregamento Automático: False
                    """.trimIndent())
                }
            }

            "apc" -> {
                valor = ("""
|   Capacidade: ${detalhesVeiculo[11]}
                """.trimIndent())
            }
        }
    } catch (e: Exception) {
        valor = "|   Informação Adicional: ${alvo} (Não encontrada)"
    }
    return valor
}

fun informacaoNacao (alvo: String) : String {
    var nacao = ""
    var valor : String = ""

    try {
        var nacoes = lerFicheiro("nacoes")

        for (row in nacoes) {
            if (separadorFicheiro(row)[0] == alvo) {
                nacao = row
            }
        }
        var detalhesNacao = separadorFicheiro(nacao)

        valor = ("""
|   Origem: ${detalhesNacao[1]} (${detalhesNacao[0]})
|   Continente: ${detalhesNacao[2]}
        """.trimIndent())
    } catch (e: Exception) {
        valor = "|   Origem: ${alvo} (Nação não encontrada)"
    }
    return valor
}

fun informacaoArmamento (alvo: String) : String {
    var armamento = ""
    var valor : String = ""

    try {
        var nacoes = lerFicheiro("nacoes")

        for (row in nacoes) {
            if (separadorFicheiro(row)[0] == alvo) {
                armamento = row
            }
        }
        var detalhes = separadorFicheiro(armamento)

        valor = ("""
|   Nome: ${detalhes[0]}
|   Tipo: ${detalhes[1]}
|   Origem: ${detalhes[3]}
|   Servico: ${detalhes[2]}
|   Calibre: ${detalhes[4]}
        """.trimIndent())
    } catch (e: Exception) {
        valor = "|   Origem: ${alvo} (Armamento não encontrado)"
    }
    return valor
}