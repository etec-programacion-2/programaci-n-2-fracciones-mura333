package org.example

class Fraccion(val numerador: Int, val denominador: Int) {

    init {
        require(denominador != 0) { "El denominador no puede ser cero" }
    }

    // Método para mostrar la fracción como texto
    fun mostrar(): String = "$numerador/$denominador"

    // Sobrescribe toString() para imprimir la fracción
    override fun toString(): String = mostrar()

    // Operador suma
    operator fun plus(other: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * other.denominador + other.numerador * this.denominador
        val nuevoDenominador = this.denominador * other.denominador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }

    // Operador resta
    operator fun minus(other: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * other.denominador - other.numerador * this.denominador
        val nuevoDenominador = this.denominador * other.denominador
        return Fraccion(nuevoNumerador, nuevoDenominador)
    }
}

fun main() {
    // Parte 1: creación y visualización de una fracción
    val f1 = Fraccion(-4, 3)
    println("La fracción es: ${f1}")

    // Parte 2: suma y resta con otra fracción
    val f2 = Fraccion(6, 1)  // Aseguramos denominador válido
    println("La suma es: ${f1 + f2}")
    println("La resta es: ${f1 - f2}")

    // Parte 3: mostrar usando el método mostrar()
    println("Usando mostrar(): ${f1.mostrar()}")
}
