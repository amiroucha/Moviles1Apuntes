package com.example.moviles1apuntes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity() {
    val bebida = Bebida("fotos/coca_cola.jpg", 3.0f, "CocaCola Normal", 0.5f, false)
    val c1 = Bebida("fotos/marinera.jpg", 2.50f, "maaaaaar", 0.5f, false)
    val c2 = Bebida("fotos/combinado1.jpg", 3.0f, "menu 1", 0.5f, false)
    val c3 = Bebida("fotos/tartaQueso.jpg", 3.0f, "tartita", 0.5f, false)
    val comanda = Comanda(2)

    println("Comanda: " $co)
}

//CLASE
//quitamos data porque no es compatible con abstract
abstract class Producto (open val foto: String = "", open var precio: Float = 0.0f, open val descripcion:String ="")
{
    constructor(p:Producto): this(p.foto,p.precio, p.descripcion )

    companion object{ //para que sean estaticos
        val IVA = 1.21f
        val IVA_REDUCIDO = 1.1f
    }

}

//----------------------------------------------------------------------------
data class Bebida(override val foto: String = "", override var precio: Float = 0.0f, override val descripcion:String ="" ,  val tamLitros:Float=3.33f, val alcoholica:Boolean = false):Producto(foto, precio, descripcion)//para llamar alconstructor de la clase producto
{
    init{
        precio = when(alcoholica)
        {
            false-> this.precio * IVA_REDUCIDO
            true -> this.precio * IVA
        }
    }
}
//----------------------------------------------------------------------------
enum class TipoComida{ //enumerado
    ENTRANTE, PRINCIPAL, POSTRE
}
//----------------------------------------------------------------------------
data class Comida(override val foto: String, override var precio: Float, override val descripcion:String , val tipoComida: TipoComida= TipoComida.ENTRANTE):Producto(foto, precio, descripcion)//para llamar alconstructor de la clase producto
{

}

//----------------------------------------------------------------------------
//lo que se quiere es inicializar siempre la lista vacia
data class Comanda(var numMesa:Int =1, var numComensales:Int=1)//esto actua como constructor primario
//pero alli solo se puede inicializar las variables
{
    var listaProductos: MutableList<Producto> = mutableListOf()

    init {//cada vez que llames al constructor primario ejecuta el init despues de el
        listaProductos = mutableListOf()
    }

    fun añadirProducto(p:Producto)
    {
        listaProductos.add(p)
    }

    fun eliminarProductos(p:Producto)
    {
        listaProductos.remove(p)
    }

}
//----------------------------------------------------------------------------
//completar con el cod de clase, que lo va a subir


























/* COSAS HECHAS EN CASA
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

 */