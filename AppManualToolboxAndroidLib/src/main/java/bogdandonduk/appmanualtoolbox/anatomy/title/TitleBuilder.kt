package bogdandonduk.appmanualtoolbox.anatomy.title

class TitleBuilder internal constructor() {
    @PublishedApi
    internal var model = Title(
        "Welcome!"
    )

    inline fun setText(modification: (oldText: String) -> String) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    fun build() = model
}