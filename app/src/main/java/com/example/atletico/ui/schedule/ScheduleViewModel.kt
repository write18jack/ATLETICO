package com.example.atletico.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ScheduleViewModel : ViewModel() {

    private var _standings = MutableStateFlow<List<Display>>(emptyList())
    val standings: StateFlow<List<Display>> get() = _standings

    init {
        getStandings()
    }

    private fun getStandings(){
        viewModelScope.launch {
            try {
                toDisplay(ResultApi.retrofitService.getStandings())
            }catch (e: Exception){
                _standings.value = ArrayList()
            }
        }
    }

    private fun toDisplay(response: Response<Standings>){

        _standings.value = response.body()?.data?.table!!.map {
            Display(
                rank = it.rank,
                name = it.name,
            )
        }

    }
}