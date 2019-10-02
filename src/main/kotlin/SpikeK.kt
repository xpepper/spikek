package spike

class SpikeK() {
    val ml = mutableListOf(1, 2, 3)
}

fun boringMethodWithAList(list: List<*>) {
    println(list::class.java)
    println("before $list")
    naughtyFun(list)
    println("after $list")
}

fun <T> naughtyFun(list: List<T>) {

    if (list.size > 1 && list is MutableList<T>) {
        val e = list[0]
        list[0] = list[1]
        list[1] = e
    }
}

data class NonMutableList<T>(private val originalList: List<T>) : List<T> by originalList


fun main() {
    val mutableList = NonMutableList(listOf(1, 2, 3, 4))
    boringMethodWithAList(mutableList)


}


//
//fun main() {
//    val name: String = SpikeJ().name()
//    println(name.length)
//
//}
