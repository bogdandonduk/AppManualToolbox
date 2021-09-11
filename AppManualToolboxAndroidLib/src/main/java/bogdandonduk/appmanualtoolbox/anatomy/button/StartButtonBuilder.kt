package bogdandonduk.appmanualtoolbox.anatomy.button

import android.graphics.drawable.Drawable

class StartButtonBuilder internal constructor() {
    @PublishedApi
    internal var model = StartButton(
        "Start"
    )

    inline fun setText(modification: (oldText: String) -> String) = this.apply {
        model.text = modification.invoke(model.text)
    }

    fun getText() = model.text

    inline fun setTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.textColor = modification.invoke(model.textColor)
    }

    fun getTextColor() = model.textColor

    inline fun setBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.backgroundColor = modification.invoke(model.backgroundColor)
    }

    fun getBackgroundColor() = model.backgroundColor

    inline fun setBackgroundDrawable(modification: (oldIcon: Drawable?) -> Drawable?) = this.apply {
        model.backgroundDrawable = modification.invoke(model.backgroundDrawable)
    }

    fun getBackgroundDrawable() = model.backgroundDrawable

    inline fun setBackgroundDrawableTintColor(modification: (oldColor: Int?) -> Int?) = this.apply {
        model.backgroundDrawableTintColor = modification.invoke(model.backgroundDrawableTintColor)
    }

    fun getBackgroundDrawableTintColor() = model.backgroundDrawableTintColor

    fun setCornerRadii(topLeftPx: Int, topRightPx: Int, bottomRightPx: Int, bottomLeftPx: Int) = this.apply {
        model.cornerRadiusBottomLeftPx = topLeftPx
        model.cornerRadiusBottomRightPx = topRightPx
        model.cornerRadiusBottomRightPx = bottomRightPx
        model.cornerRadiusBottomLeftPx = bottomLeftPx
    }

    fun setCornerRadiusTopLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopLeftPx = modification.invoke(model.cornerRadiusTopLeftPx)
    }

    fun getCornerRadiusTopLeftPx() = model.cornerRadiusTopLeftPx

    fun setCornerRadiusTopRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusTopRightPx = modification.invoke(model.cornerRadiusTopRightPx)
    }

    fun getCornerRadiusTopRightPx() = model.cornerRadiusTopRightPx

    fun setCornerRadiusBottomRightPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomRightPx = modification.invoke(model.cornerRadiusBottomRightPx)
    }

    fun getCornerRadiusBottomRightPx() = model.cornerRadiusBottomRightPx

    fun setCornerRadiusBottomLeftPx(modification: (oldRadius: Int) -> Int) = this.apply {
        model.cornerRadiusBottomLeftPx = modification.invoke(model.cornerRadiusBottomLeftPx)
    }

    fun getCornerRadiusBottomLeftPx() = model.cornerRadiusBottomLeftPx

    fun build() = model
}