fun String.transformWith(function: (String) -> List<String>): List<String> {
    return function(this)
}

val sout: String.() -> Unit = { println(this) }

fun main(args: Array<String>) {
    val tr = "https://engineering.facile.it/blog/eng/kotlin-dsl/".transformWith(splitBy("/"))
    println(tr)

    "ciao".sout()

}

private fun splitBy(delimiter: String): (String) -> List<String> = { it.split(delimiter) }
