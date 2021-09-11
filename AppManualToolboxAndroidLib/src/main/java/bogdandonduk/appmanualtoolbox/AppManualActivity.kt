package bogdandonduk.appmanualtoolbox

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import bogdandonduk.appbartoolboxandroidlib.SlidrHandler
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBar
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBarHandler
import bogdandonduk.appbartoolboxandroidlib.appbar.AppBarToolbox
import bogdandonduk.appbartoolboxandroidlib.drawer.AppBarDrawerToggle
import bogdandonduk.appmanualtoolbox.anatomy.page.PageAdapter
import bogdandonduk.appmanualtoolbox.databinding.ActivityAppManualBinding
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipConfig
import bogdandonduk.tooltiptoolboxlib.TooltipToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipsHandler
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerActivity
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInstanceViewModelHandler
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface
import top.defaults.drawabletoolbox.DrawableBuilder

class AppManualActivity : BaseViewBindingHandlerActivity<ActivityAppManualBinding>(
    {
        ActivityAppManualBinding.inflate(it)
    }
), SingleAutomaticInitializationWithInstanceViewModelHandler<AppManualActivityViewModel>,
    AppBarHandler,
    SlidrHandler,
    TooltipsHandler {
    override var viewModelNewInstance = AppManualActivityViewModel()

    override var slidrInterface: SlidrInterface? = null

    override var appBar: AppBar? = null

    override var appBarDrawerToggle: AppBarDrawerToggle? = null

    override var showOptionsMenu = false

    override var homeAsUpIndicatorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInitializedViewModel(viewModelStore)

        AppManualToolbox.currentAppManualModel?.run {
            if(isSlidable) {
                viewBinding.activityAppManualTitleTextView.visibility = View.INVISIBLE

                getInitializedAppBar(this@AppManualActivity, viewBinding.activityAppManualToolbar)
                    .modifyAsActionBar {
                        it.setDisplayHomeAsUpEnabled(true)
                        it.setDisplayShowTitleEnabled(false)
                    }

                initBackNavigation(this@AppManualActivity)

                CommonToolbox.applyColorFilter(AppBarToolbox.getHomeAsUpIndicatorIconDrawable(viewBinding.activityAppManualToolbar)!!, iconTintColor)

                homeAsUpIndicatorView = AppBarToolbox.getHomeAsUpIndicatorAsView(viewBinding.activityAppManualToolbar)

                homeAsUpIndicatorView?.run {
                    background = DrawableBuilder()
                        .cornerRadius(1000000)
                        .ripple()
                        .rippleColor(CommonToolbox.getRippleColorByLuminance(this@AppManualActivity, backgroundColor))
                        .build()

                    setOnLongClickListener {
                        CommonToolbox.vibrateOneShot(this@AppManualActivity)

                        configureGoBackTooltip().show(this@AppManualActivity, it)

                        true
                    }
                }

                slidrInterface = Slidr.attach(this@AppManualActivity)
            } else {
                viewBinding.activityAppManualTitleTextView.run {
                    text = title.text
                    setTextColor(title.textColor)
                }
            }

            if(backgroundDrawable != null) {
                viewBinding.root.background = backgroundDrawable

                if(backgroundDrawableTintColor != null)
                    CommonToolbox.applyColorFilter(viewBinding.root.background, backgroundDrawableTintColor!!)
            } else
                viewBinding.root.setBackgroundColor(backgroundColor)

            viewBinding.activityAppManualStartButton.run {
                if(startButton.backgroundDrawable != null) {
                    if (startButton.backgroundDrawableTintColor != null)
                        CommonToolbox.applyColorFilter(
                            startButton.backgroundDrawable!!,
                            startButton.backgroundDrawableTintColor!!
                        )

                    background = DrawableBuilder()
                        .baseDrawable(startButton.backgroundDrawable!!)
                        .ripple()
                        .rippleColor(
                            CommonToolbox.getRippleColorByLuminance(
                                this@AppManualActivity,
                                (startButton.backgroundDrawable as ColorDrawable).color
                            )
                        )
                        .cornerRadii(
                            startButton.cornerRadiusTopLeftPx,
                            startButton.cornerRadiusTopRightPx,
                            startButton.cornerRadiusBottomRightPx,
                            startButton.cornerRadiusBottomLeftPx
                        )
                        .build()
                } else
                    background = DrawableBuilder()
                        .solidColor(startButton.backgroundColor)
                        .ripple()
                        .rippleColor(
                            CommonToolbox.getRippleColorByLuminance(
                                this@AppManualActivity,
                                startButton.backgroundColor
                            )
                        )
                        .cornerRadii(
                            startButton.cornerRadiusTopLeftPx,
                            startButton.cornerRadiusTopRightPx,
                            startButton.cornerRadiusBottomRightPx,
                            startButton.cornerRadiusBottomLeftPx
                        )
                        .build()

                text = startButton.text
                setTextColor(startButton.textColor)

                elevation = 4f

                setOnClickListener {
                    if(AppManualToolbox.currentContinuationActivity != null) {
                        CommonToolbox.openActivity(
                            this@AppManualActivity,
                            AppManualToolbox.currentContinuationActivity!!
                        )

                        AppManualToolbox.currentContinuationActivity != null

                        AppManualToolbox.setFirstLaunchHandled(this@AppManualActivity)
                    }

                    finish()
                }
            }

            viewBinding.activityAppManualContentViewPager2.run {
                adapter = PageAdapter(this@AppManualActivity)

                viewBinding.activityAppManualSpringDotsIndicator.let {
                    it.setDotIndicatorColor(dotsIndicatorColor)
                    it.setStrokeDotsIndicatorColor(dotsIndicatorStrokeColor)

                    it.setViewPager2(this)
                }
            }

            CommonToolbox.setOnTouchListeners(
                { _, event ->
                    if(event.action == MotionEvent.ACTION_DOWN)
                        dismissTooltips()
                },
                viewBinding.root,
                viewBinding.activityAppManualToolbar,
                homeAsUpIndicatorView,
                viewBinding.activityAppManualTitleTextView,
                viewBinding.activityAppManualContentViewPager2,
                viewBinding.activityAppManualSpringDotsIndicator,
                viewBinding.activityAppManualStartButton
            )
        }
    }

    override fun continueTooltips(vararg excludedKeys: String) {
        if(homeAsUpIndicatorView != null) getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_GO_BACK]!!.`continue`(this@AppManualActivity,homeAsUpIndicatorView!!)
    }

    override fun dismissTooltips(vararg excludedKeys: String) {
        TooltipToolbox.dismissTooltip(getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_GO_BACK]!!.getKey())
    }

    private fun configureGoBackTooltip() = getInitializedViewModel(viewModelStore).tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_GO_BACK]!!
        .apply {
            AppManualToolbox.currentAppManualModel!!.run {
                setText {
                    tooltipTextGoBack
                }

                setTextColor {
                    tooltipTextColor
                }

                setBackgroundColor {
                    tooltipBackgroundColor
                }

                setOnClickAction { _, _ ->
                    onBackPressed()
                }
            }
        }
}