package bogdandonduk.appmanualtoolbox.anatomy.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import bogdandonduk.appmanualtoolbox.AppManualActivity
import bogdandonduk.appmanualtoolbox.AppManualToolbox
import bogdandonduk.appmanualtoolbox.databinding.FragmentPageBinding
import bogdandonduk.commontoolboxlib.CommonToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipsHandler
import bogdandonduk.viewdatabindingwrapperslib.BaseViewBindingHandlerFragment

internal class PageFragment : BaseViewBindingHandlerFragment<FragmentPageBinding>(
    { layoutInflater: LayoutInflater, viewGroup: ViewGroup? ->
        FragmentPageBinding.inflate(layoutInflater, viewGroup, false)
    }
) {
    var pagePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pagePosition = requireArguments().getInt(AppManualToolbox.KEY_PAGE_NUMBER)
    }

    private fun draw() {
        AppManualToolbox.currentAppManualModel!!.pageItems[pagePosition].let {
            if(it.bigImageDrawable != null) {
                viewBinding.fragmentPageBigImageImageView.run {
                    visibility = View.VISIBLE

                    setImageDrawable(it.bigImageDrawable!!)

                    if(it.bigImageDrawableTintColor != null)
                        CommonToolbox.applyColorFilter(drawable, it.bigImageDrawableTintColor!!)
                }
            }

            viewBinding.fragmentPageTextTextView.run {
                text = it.text
                viewBinding.fragmentPageTextTextView.setTextColor(it.textColor)
            }

            (requireActivity() as? AppManualActivity)?.slidrInterface?.run {
                if(pagePosition > 0)
                    lock()
                else
                    unlock()
            }

            CommonToolbox.setOnTouchListeners(
                { _, event ->
                    if(event.action == MotionEvent.ACTION_DOWN)
                        (requireActivity() as? TooltipsHandler)?.dismissTooltips()
                },
                viewBinding.root,
                viewBinding.fragmentPageBigImageImageView,
                viewBinding.fragmentPageTextTextView,
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        draw()
    }

    override fun onResume() {
        super.onResume()

        draw()
    }
}