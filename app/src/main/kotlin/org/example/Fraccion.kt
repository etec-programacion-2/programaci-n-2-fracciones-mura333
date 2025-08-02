package org.example

class Fraccion(private var _numerador: Int, private var _denominador: Int) {

    var numerador: Int = _numerador
        get() = field
        set(value) { field = value }

    var denominador: Int = _denominador
        get() = field
        set(value) {
            if (value == 0) {
                throw IllegalArgumentException("El denominador no puede ser cero")
            }
            field = value
        }

    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
    }

    override fun toString(): String {
        return "$numerador/$denominador"
    }

    fun mostrar() {
        println(this.toString())
    }

    private fun _simplificar(numerador: Int, denominador: Int): Fraccion {
        var a = numerador
        var b = denominador
        while (b != 0) {
            val temp = b
            b = a % b
            a = temp
        }
        val mcd = a
        return Fraccion(numerador / mcd, denominador / mcd)
    }

    operator fun plus(otra: Fraccion): Fraccion {
        val sumaNumerador = this.numerador * otra.denominador + otra.numerador * this.denominador
        val sumaDenominador = this.denominador * otra.denominador
        return _simplificar(sumaNumerador, sumaDenominador)
    }

    operator fun minus(otra: Fraccion): Fraccion {
        val restaNumerador = this.numerador * otra.denominador - otra.numerador * this.denominador
        val restaDenominador = this.denominador * otra.denominador
        return _simplificar(restaNumerador, restaDenominador)
    }
}

fun main() {
    val f1 = Fraccion(1, 2)
    val f2 = Fraccion(1, 3)

    val suma = f1 + f2
    val resta = f1 - f2

    println("Fracción 1: $f1")
    println("Fracción 2: $f2")
    println("Suma: $suma")
    println("Resta: $resta")
}
