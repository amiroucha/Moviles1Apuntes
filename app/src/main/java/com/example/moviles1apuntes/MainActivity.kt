package com.example.moviles1apuntes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

open class Producto{//open para que se pueda heredar de la clase
    //declaro las variables
    protected var foto: String = ""
        get() = field
        set(value) {
            field = value
        }
    protected var precio: Float = 0.0F
        set(value) {
            field = value
        }
    protected var descripcion: String = ""
        get() = field
        set(value) {
            field = value
        }
    fun getPrecio(): Float {
        return precio
    }
    //para que sean estaticas hay que usar el companion object
    companion object{
        public val FINALIVA: Float = 1.21f
        public val FINALIVAREDUCIDO: Float= 1.10f
    }

    //constructores
    constructor() {
        // No inicializa nada porque ya tienen valores por defecto
    }

    // Constructor con parámetros
    constructor(foto: String, precio: Float, descripcion: String) {
        this.foto = foto
        this.precio = precio
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "Producto(foto='$foto', precio=$precio, descripcion='$descripcion')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Producto) return false

        if (foto != other.foto) return false
        if (precio != other.precio) return false
        if (descripcion != other.descripcion) return false
        return true
    }


}

class Comanda{
    constructor(numMesa: Int, numComensales: Int) {
        this.numMesa = numMesa
        this.numComensales = numComensales
    }
    constructor()

    private var listaProductos: MutableList<Producto> = mutableListOf()
        get() = field
    private var numMesa : Int = 0
    private var numComensales : Int = 0

    fun añadirProducto(p: Producto) {
        listaProductos.add(p)
    }

    fun eliminarProducto(p: Producto) {
        listaProductos.remove(p)
    }

    // Getters y Setters para numMesa y numComensales
    fun getNumMesa(): Int {
        return numMesa
    }

    fun setNumMesa(numMesa: Int) {
        this.numMesa = numMesa
    }

    fun getNumComensales(): Int {
        return numComensales
    }

    fun setNumComensales(numComensales: Int) {
        this.numComensales = numComensales
    }

    fun getPrecioComanda(descuento: Int): Float {
        var precioTotal: Float = 0.0f
        for (producto in listaProductos) {
            precioTotal += producto.getPrecio()
        }
        // Aplicar el descuento
        return precioTotal * (1 - (descuento / 100.0f))
    }

    // Método toString()
    override fun toString(): String {
        return "Comanda(numMesa=$numMesa, numComensales=$numComensales, listaProductos=$listaProductos)"
    }
    // Método equals() para comparar dos comandas
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Comanda) return false

        if (numMesa != other.numMesa) return false
        if (numComensales != other.numComensales) return false
        if (listaProductos != other.listaProductos) return false

        return true
    }

    override fun hashCode(): Int {
        var result = listaProductos.hashCode()
        result = 31 * result + numMesa
        result = 31 * result + numComensales
        return result
    }

}