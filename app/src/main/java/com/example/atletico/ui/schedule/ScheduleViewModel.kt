package com.example.atletico.ui.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ScheduleViewModel : ViewModel() {

    private var _standings = MutableStateFlow<List<DisplayTable>>(emptyList())
    val standings: StateFlow<List<DisplayTable>> get() = _standings

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

    private fun toDisplay(response: Response<Table>){
        _standings.value = response.body()?.records!!.map {
            DisplayTable(
                team = it.team,
                played = it.played.toString(),
                win = it.win.toString(),
                draw = it.draw.toString(),
                loss = it.loss.toString(),
                points = it.points.toString()
            )
        }
    }
}