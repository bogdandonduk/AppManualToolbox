package bogdandonduk.appmanualtoolbox

import androidx.lifecycle.ViewModel
import bogdandonduk.tooltiptoolboxlib.TooltipBuilder
import bogdandonduk.tooltiptoolboxlib.TooltipConfig
import bogdandonduk.tooltiptoolboxlib.TooltipToolbox
import bogdandonduk.tooltiptoolboxlib.TooltipsPersistableHandler

class AppManualActivityViewModel : ViewModel(), TooltipsPersistableHandler {
    override val tooltipBuilders =  mutableMapOf<String, TooltipBuilder>().apply {
        this[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_GO_BACK] = TooltipToolbox.buildTooltip()
    }

    override fun onCleared() {
        AppManualToolbox.currentAppManualModel = null
        AppManualToolbox.currentContinuationActivity = null

        TooltipToolbox.dismissTooltip(tooltipBuilders[TooltipConfig.KeysExtensionVocabulary.KEY_TOOLTIP_GO_BACK]!!.getKey())
    }
}