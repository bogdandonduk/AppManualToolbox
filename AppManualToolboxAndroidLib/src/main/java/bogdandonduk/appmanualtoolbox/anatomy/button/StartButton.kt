package bogdandonduk.appmanualtoolbox.anatomy.button

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class StartButton(
    var text: String,
    @ColorInt var textColor: Int = Color.WHITE,

    @ColorInt var backgroundColor: Int = Color.argb(100, 33, 150, 243),
    var backgroundDrawable: Drawable? = null,
    @ColorInt var backgroundDrawableTintColor: Int? = null,

    var cornerRadiusTopLeftPx: Int = 30,
    var cornerRadiusTopRightPx: Int = 30,
    var cornerRadiusBottomRightPx: Int = 30,
    var cornerRadiusBottomLeftPx: Int = 30
)