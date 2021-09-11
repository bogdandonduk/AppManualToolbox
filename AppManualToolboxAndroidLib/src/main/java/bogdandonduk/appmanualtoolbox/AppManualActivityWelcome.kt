package bogdandonduk.appmanualtoolbox

import android.graphics.drawable.ColorDrawable
import bogdandonduk.appmanualtoolbox.anatomy.page.PageAdapter
import bogdandonduk.appmanualtoolbox.databinding.ActivityAppManualBinding
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerActivity
import bogdandonduk.viewmodelwrapperslib.automatic.SingleAutomaticInitializationWithInstanceViewModelHandler
import top.defaults.drawabletoolbox.DrawableBuilder

class AppManualActivityWelcome : BaseViewBindingHandlerActivity<ActivityAppManualBinding>(
    {
        ActivityAppManualBinding.inflate(it)
    }
), SingleAutomaticInitializationWithInstanceViewModelHandler<AppManualActivityViewModel>{
    override var viewModelNewInstance = AppManualActivityViewModel()

    override fun onResume() {
        super.onResume()

        getInitializedViewModel(viewModelStore)

        AppManualToolbox.currentAppManualModel?.run {
            viewBinding.activityAppManualTitleTextView.run {
                text = title.text
                setTextColor(title.textColor)
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
                                this@AppManualActivityWelcome,
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
                                this@AppManualActivityWelcome,
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
                            this@AppManualActivityWelcome,
                            AppManualToolbox.currentContinuationActivity!!
                        )

                        AppManualToolbox.currentContinuationActivity != null

                        AppManualToolbox.setFirstLaunchHandled(this@AppManualActivityWelcome)
                    }

                    finish()
                }
            }

            viewBinding.activityAppManualContentViewPager2.run {
                adapter = PageAdapter(this@AppManualActivityWelcome)

                viewBinding.activityAppManualSpringDotsIndicator.let {
                    it.setDotIndicatorColor(dotsIndicatorColor)
                    it.setStrokeDotsIndicatorColor(dotsIndicatorStrokeColor)

                    it.setViewPager2(this)
                }
            }
        }
    }
}