package bogdandonduk.appmanualtoolbox

import android.graphics.drawable.Drawable
import bogdandonduk.appmanualtoolbox.anatomy.button.StartButton
import bogdandonduk.appmanualtoolbox.anatomy.page.PageItem
import bogdandonduk.appmanualtoolbox.anatomy.title.Title

class AppManualModelBuilder internal constructor() {
    @PublishedApi
    internal var model = AppManualModel()

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

    inline fun setIconTintColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.iconTintColor = modification.invoke(model.iconTintColor)
    }

    fun getIconTintColor() = model.iconTintColor

    inline fun setTooltipTextGoBack(modification: (oldText: String) -> String) = this.apply {
        model.tooltipTextGoBack = modification.invoke(model.tooltipTextGoBack)
    }

    fun getTooltipTextGoBack() = model.tooltipTextGoBack

    inline fun setTooltipTextColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.tooltipTextColor = modification.invoke(model.tooltipTextColor)
    }

    fun getTooltipTextColor() = model.tooltipTextColor

    inline fun setTooltipBackgroundColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.tooltipBackgroundColor = modification.invoke(model.tooltipBackgroundColor)
    }

    fun getTooltipBackgroundColor() = model.tooltipBackgroundColor

    inline fun setIsSlidable(modification: (oldValue: Boolean) -> Boolean) = this.apply {
        model.isSlidable = modification.invoke(model.isSlidable)
    }

    fun getIsSlidable() = model.isSlidable

    inline fun setDotsIndicatorColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.dotsIndicatorColor = modification.invoke(model.dotsIndicatorColor)
    }

    fun getDotsIndicatorColor() = model.dotsIndicatorColor

    inline fun setDotsIndicatorStrokeColor(modification: (oldColor: Int) -> Int) = this.apply {
        model.dotsIndicatorStrokeColor = modification.invoke(model.dotsIndicatorStrokeColor)
    }

    fun getDotsIndicatorStrokeColor() = model.dotsIndicatorStrokeColor

    inline fun setTitle(modification: (oldStartButton: Title) -> Title) = this.apply {
        model.title = modification.invoke(model.title)
    }

    fun getTitle() = model.title

    inline fun setStartButton(modification: (oldStartButton: StartButton) -> StartButton) = this.apply {
        model.startButton = modification.invoke(model.startButton)
    }

    fun getStartButton() = model.startButton

    fun setAllPages(modification: (oldPageItems: MutableList<PageItem>) -> MutableList<PageItem>) = this.apply {
        model.pageItems = modification.invoke(model.pageItems)
    }

    fun getAllPageItems() = model.pageItems

    fun build() = model
}