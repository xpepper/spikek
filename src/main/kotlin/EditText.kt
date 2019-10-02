data class EditText(
    var text: String = "",
    var hint: String = ""
) {
    private var onTextChanged: (String) -> Unit = {}

    internal fun onTextChanged(builder: (String) -> Unit) {
        onTextChanged = builder
    }
}

fun editText(builder: EditText.() -> Unit): EditText {
    return EditText().apply(builder)
}

fun main(args: Array<String>) {
    val editText = editText {
        text = "Hello"
        hint = "Enter something"
        onTextChanged { text -> print("You wrote: $text") }
    }
}

