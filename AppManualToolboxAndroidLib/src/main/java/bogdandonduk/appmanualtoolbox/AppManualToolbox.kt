package bogdandonduk.appmanualtoolbox

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import bogdandonduk.appmanualtoolbox.anatomy.button.StartButtonBuilder
import bogdandonduk.appmanualtoolbox.anatomy.page.PageItemBuilder
import bogdandonduk.appmanualtoolbox.anatomy.title.TitleBuilder
import bogdandonduk.commontoolboxlib.CommonToolbox

object AppManualToolbox {
    private const val delimiter = "_"

    private const val LIBRARY_SHARED_PREFS_SUFFIX = "${delimiter}shared${delimiter}prefs${delimiter}bogdandonduk.appmanualtoolbox"

    private const val IS_FIRST_LAUNCH = "is${delimiter}first${delimiter}launch$LIBRARY_SHARED_PREFS_SUFFIX"

    internal var currentContinuationActivity: Class<out Activity>? = null

    internal var currentAppManualModel: AppManualModel? = null

    private fun getPreferences(context: Context) : SharedPreferences =
        context.getSharedPreferences(context.packageName + LIBRARY_SHARED_PREFS_SUFFIX, Context.MODE_PRIVATE)

    private fun isFirstLaunch(context: Context) = getPreferences(context).getBoolean(IS_FIRST_LAUNCH, true)

    internal fun setFirstLaunchHandled(context: Context) = getPreferences(context).edit().putBoolean(IS_FIRST_LAUNCH, false).apply()

    internal fun removeFirstLaunchInfo(context: Context) = getPreferences(context).edit().remove(IS_FIRST_LAUNCH).apply()

    internal const val KEY_PAGE_NUMBER = "key_page_number"

    fun handleFirstLaunch(
        currentActivity: Activity,
        model: AppManualModel,
        continuationActivityClass: Class<out Activity>? = null,
        finishCurrentActivity: Boolean = true
    ) =
        if(isFirstLaunch(currentActivity)) {
            currentAppManualModel = model

            CommonToolbox.openActivity(currentActivity, AppManualActivityWelcome::class.java, afterAction = {
                if(finishCurrentActivity) currentActivity.finish()

                currentContinuationActivity = continuationActivityClass ?: currentActivity::class.java
            })

            true
        } else false

    fun openManualActivity(context: Context, model: AppManualModel) {
        currentAppManualModel = model

        CommonToolbox.openActivity(context, AppManualActivity::class.java)
    }

    fun buildAppManual() = AppManualModelBuilder()

    fun buildStartButton() = StartButtonBuilder()

    fun buildTitle() = TitleBuilder()

    fun buildPageItem() = PageItemBuilder()
}