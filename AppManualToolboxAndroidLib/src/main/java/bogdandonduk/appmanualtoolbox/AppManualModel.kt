package bogdandonduk.appmanualtoolbox

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import bogdandonduk.appmanualtoolbox.anatomy.button.StartButton
import bogdandonduk.appmanualtoolbox.anatomy.page.PageItem
import bogdandonduk.appmanualtoolbox.anatomy.title.Title

class AppManualModel(
    @ColorInt var backgroundColor: Int = Color.WHITE,
    var backgroundDrawable: Drawable? = null,
    @ColorInt var backgroundDrawableTintColor: Int? = null,

    @ColorInt var iconTintColor: Int = Color.BLACK,

    var tooltipTextGoBack: String = "Go back",
    @ColorInt var tooltipTextColor: Int = Color.WHITE,
    @ColorInt var tooltipBackgroundColor: Int = Color.BLACK,

    var isSlidable: Boolean = false,

    @ColorInt var dotsIndicatorColor: Int = Color.BLACK,
    @ColorInt var dotsIndicatorStrokeColor: Int = Color.BLACK,

    var pageItems: MutableList<PageItem> = mutableListOf(),

    var title: Title = Title(
        "Welcome!"
    ),
    var startButton: StartButton = StartButton(
        "Start"
    )
)