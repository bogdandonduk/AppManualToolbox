package bogdandonduk.appmanualtoolbox.anatomy.page

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import bogdandonduk.appmanualtoolbox.AppManualToolbox

internal class PageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = AppManualToolbox.currentAppManualModel!!.pageItems.size

    override fun createFragment(position: Int) =
        PageFragment().apply {
            arguments = bundleOf(
                AppManualToolbox.KEY_PAGE_NUMBER to position
            )
        }
}