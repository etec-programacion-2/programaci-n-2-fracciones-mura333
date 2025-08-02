package org.example

class Fraccion(val numerador: Int, val denominador: Int) : Comparable<Fraccion> {

    init {
        require(denominador != 0) { "El denominador no puede ser cero" }
    }

    fun mostrar(): String = "$numerador/$denominador"

    override fun toString(): String = mostrar()

    operator fun plus(other: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * other.denominador + other.numerador * this.denominador
        val nuevoDenominador = this.denominador * other.denominador
        return simplificar(nuevoNumerador, nuevoDenominador)
    }

    operator fun minus(other: Fraccion): Fraccion {
        val nuevoNumerador = this.numerador * other.denominador - other.numerador * this.denominador
        val nuevoDenominador = this.denominador * other.denominador
        return simplificar(nuevoNumerador, nuevoDenominador)
    }

    // Parte 3: multiplicación y división
    operator fun times(other: Fraccion): Fraccion {
        return simplificar(this.numerador * other.numerador, this.denominador * other.denominador)
    }

    operator fun div(other: Fraccion): Fraccion {
        require(other.numerador != 0) { "No se puede dividir por una fracción con numerador cero" }
        return simplificar(this.numerador * other.denominador, this.denominador * other.numerador)
    }

    // Parte 4: comparación, igualdad y conversiones
    override fun compareTo(other: Fraccion): Int {
        return (this.numerador * other.denominador).compareTo(other.numerador * this.denominador)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Fraccion) return false
        val a = simplificar(this.numerador, this.denominador)
        val b = simplificar(other.numerador, other.denominador)
        return a.numerador == b.numerador && a.denominador == b.denominador
    }

    override fun hashCode(): Int {
        val simplificada = simplificar(numerador, denominador)
        return 31 * simplificada.numerador + simplificada.denominador
    }

    fun esMayor(other: Fraccion): Boolean = this > other
    fun esMenor(other: Fraccion): Boolean = this < other
    fun aDecimal(): Double = this.numerador.toDouble() / this.denominador.toDouble()

    companion object {
        fun desdeDecimal(decimal: Double, precision: Int = 10000): Fraccion {
            val num = (decimal * precision).toInt()
            val den = precision
            return simplificar(num, den)
        }

        private fun simplificar(numerador: Int, denominador: Int): Fraccion {
            var a = kotlin.math.abs(numerador)
            var b = kotlin.math.abs(denominador)
            while (b != 0) {
                val temp = b
                b = a % b
                a = temp
            }
            val signo = if (denominador < 0) -1 else 1
            return Fraccion((numerador / a) * signo, (denominador / a) * signo)
        }
    }
}

fun main() {
    val f1 = Fraccion(-4, 3)
    println("La fracción es: $f1")

    val f2 = Fraccion(6, 1)
    println("La suma es: ${f1 + f2}")
    println("La resta es: ${f1 - f2}")

    println("La multiplicación es: ${f1 * f2}")
    println("La división es: ${f1 / f2}")

    println("La comparación es: ${f1.compareTo(f2)}")
    println("La igualdad es: ${f1 == f2}")
    println("f1 es mayor que f2: ${f1.esMayor(f2)}")
    println("f1 es menor que f2: ${f1.esMenor(f2)}")
    println("f1 en decimal: ${f1.aDecimal()}")

    val d1 = 1.75
    println("Desde decimal $d1 a fracción: ${Fraccion.desdeDecimal(d1)}")

    println("Usando mostrar(): ${f1.mostrar()}")
}
