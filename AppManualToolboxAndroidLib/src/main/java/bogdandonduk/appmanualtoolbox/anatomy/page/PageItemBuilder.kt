package bogdandonduk.appmanualtoolbox.anatomy.page

import android.graphics.drawable.Drawable

class PageItemBuilder internal constructor() {
    @PublishedApi
    internal var model = PageItem(
        text = "This app is amazing"
    )

    inline fun setText(modification: (oldText: CharSequence) -> CharSequence) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setBigImageDrawable(modification: (oldDrawable: Drawable?) -> Drawable?) = this.apply {
        model.bigImageDrawable = modification.invoke(model.bigImageDrawable)
    }

    fun getBigImageDrawable() = model.bigImageDrawable

    fun setBigImageDrawableTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.bigImageDrawableTintColor = modification.invoke(model.bigImageDrawableTintColor)
    }

    fun getBigImageDrawableTintColor() = model.bigImageDrawableTintColor

    fun build() = model
}