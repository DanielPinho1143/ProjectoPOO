open class Veiculo (
    var nome : String,
    var tipo : String,
    var servico : Int,
    var origem : String,
    var mobilidade : String,
    var variante : String,
    var motor : String,
    var peso : Int,
    var velocidade : Int,
) {}

//
//
//
//

open class Combate (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    var principal: String,
    var secundario: String,
    var tripulacao: Int,
    ) : Veiculo (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade) {}

class IFV (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    principal: String,
    secundario: String,
    tripulacao: Int,

    var capacidade: Int,
) : Combate (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade, principal, secundario, tripulacao) {}

class Automatico (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    principal: String,
    secundario: String,
    tripulacao: Int,

    var modelo: String,
    var tempo: Double,
) : Combate (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade, principal, secundario, tripulacao) {}

//
//
//
//

open class Transporte (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    var armamento: Boolean,
    var capacidade: Int,
) : Veiculo (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade) {}

class APC (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    armamento: Boolean,
    capacidade: Int,

    var tripulacao: Int,
) : Transporte (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade, armamento, capacidade) {}

//
//
//
//

open class Especial (
    nome: String,
    tipo: String,
    servico: Int,
    origem: String,
    mobilidade: String,
    variante: String,
    motor: String,
    peso: Int,
    velocidade: Int,

    var armamento: Boolean,
    var tripulacao: Int,
    var capacidade: Int,
    var motivo: String,
) : Veiculo (nome, tipo, servico, origem, mobilidade, variante, motor, peso, velocidade) {}