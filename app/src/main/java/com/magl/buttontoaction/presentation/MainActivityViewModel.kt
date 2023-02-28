package com.magl.buttontoaction.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.magl.buttontoaction.core.action.FetchActionsUseCase
import com.magl.buttontoaction.core.action.HasActionsUseCase
import com.magl.buttontoaction.core.action.LoadPrioritisedActionsUseCase
import com.magl.buttontoaction.core.action.SaveActionTimeUseCase
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.util.ConnectivityUtil
import com.magl.buttontoaction.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val loadPrioritisedActionsUseCase: LoadPrioritisedActionsUseCase,
    private val fetchActionsUseCase: FetchActionsUseCase,
    private val saveActionTimeUseCase: SaveActionTimeUseCase,
    private val hasActionsUseCase: HasActionsUseCase,
    private val connectivityUtil: ConnectivityUtil,
) : ViewModel() {

    init {
        viewModelScope.launch {
            fetchActionsUseCase.execute()
        }
    }

    private val _action = MutableLiveData<Event<ActionType>>()
    val action: LiveData<Event<ActionType>>
        get() = _action

    val hasActions = hasActionsUseCase.execute()
        .flowOn(Dispatchers.IO)
        .asLiveData()

    fun clickToAction() {
        viewModelScope.launch {
            val actions = loadPrioritisedActionsUseCase.execute()
            if (actions.isNotEmpty()) {
                if (actions[0].type == ActionType.TOAST) {
                    if (connectivityUtil.isNetworkAvailable()) {
                        _action.value = Event(actions[0].type)
                    } else if (actions.size > 1) {
                        _action.value = Event(actions[1].type)
                    }
                } else {
                    _action.value = Event(actions[0].type)
                }
            }
            Timber.d("load: ${actions}")
        }
    }

    fun updateLastTimeOpened(actionType: ActionType) {
        viewModelScope.launch {
            saveActionTimeUseCase.execute(actionType, System.currentTimeMillis())
        }
    }
}
