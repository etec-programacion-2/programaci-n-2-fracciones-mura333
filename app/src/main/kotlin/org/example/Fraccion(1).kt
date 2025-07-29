package org.example

class Fraccion(
    //Se declaran los atributos / variables como privadas
    private var _numerador: Int,
    private var _denominador: Int) {

    //Se les da el valor a las variables
    var numerador: Int = _numerador
        get() = field
        set(value) { field = value }

    var denominador: Int = _denominador
        get() = field
        set(value) { field = value
        }

    //Excepción si denominador == 0
    init { if (denominador == 0){
        throw IllegalArgumentException("El denominador no puede ser cero")}}

    //sobre escribe Metodo / función para mostrar por pantalla la fracción
    override fun toString(): String {
        return ("$_numerador/$_denominador")
    }

    //Muestra por pantalla la función toString
    fun mostrar(){
        println(this.toString())
    }

    private fun _simplificar(numerador: Int, denominador: Int): Fraccion{ //Devuelve el MCD
        var numSim = numerador
        var denSim = denominador
        while (denSim != 0) {
            val temp = denSim
            denSim = numSim % denSim
            numSim = temp
        }
        val resultN: Int = (numerador / numSim)
        val resultD: Int = (denominador / numSim)
        return Fraccion((numerador / numSim), (denominador / numSim))
    }

    //Le enseña a la clase como hacer la SUMA de fracciónes
    operator fun plus(otra: Fraccion): Fraccion { //plus es una palabra reservada

        val sumaNumerador = (this.numerador * otra.denominador) + (otra.numerador * this.denominador)
        val sumaDenominador = this.denominador * otra.denominador

        return _simplificar(sumaNumerador, sumaDenominador)
    }

    //Le enseña a la clase como hacer la RESTA de fracciónes
    operator fun minus(otra: Fraccion): Fraccion { //minus es una palabra reservada

        val restaNumerador = (this.numerador * otra.denominador) - (otra.numerador * this.denominador)
        val restaDenominador = this.denominador * otra.denominador

        return _simplificar(restaNumerador, restaDenominador)
    }
}