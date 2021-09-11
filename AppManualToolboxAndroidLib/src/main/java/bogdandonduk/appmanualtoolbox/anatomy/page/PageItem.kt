package bogdandonduk.appmanualtoolbox.anatomy.page

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

class PageItem(
    var text: CharSequence,
    @ColorInt var textColor: Int = Color.BLACK,

    var bigImageDrawable: Drawable? = null,
    @ColorInt var bigImageDrawableTintColor: Int? = null
)