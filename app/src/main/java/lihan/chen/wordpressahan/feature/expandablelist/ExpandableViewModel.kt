package lihan.chen.wordpressahan.feature.expandablelist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ExpandableViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExpandableState())
    val state = _state.asStateFlow()


}