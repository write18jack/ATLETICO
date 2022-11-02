package com.example.atletico.ui.lineup

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LineupViewModel(private val itemDao: ItemDao) : ViewModel() {

    fun allItems(): LiveData<List<EntityX>> = itemDao.getItems().asLiveData()

    //val lastId = itemDao.selectLastId()
    private var positionId: Int = 0
    private var playerId: Int = 0
    val mapPositionPlayer: MutableMap<Int, Int> = mutableMapOf()
    var positionId_check: Int? = null
    var playerId_check: Int? = null

    private val st = MutableLiveData<Int>()
    private val lst = MutableLiveData<Int>()
    private val rst = MutableLiveData<Int>()
    private val lm = MutableLiveData<Int>()
    private val lcm = MutableLiveData<Int>()
    private val cm = MutableLiveData<Int>()
    private val cdm = MutableLiveData<Int>()
    private val rcm = MutableLiveData<Int>()
    private val rm = MutableLiveData<Int>()
    private val lfb = MutableLiveData<Int>()
    private val lcb = MutableLiveData<Int>()
    private val cb = MutableLiveData<Int>()
    private val rcb = MutableLiveData<Int>()
    private val rfb = MutableLiveData<Int>()
    private val gk = MutableLiveData<Int>()
    val St: LiveData<Int> = st
    val Lst: LiveData<Int> = lst
    val Rst: LiveData<Int> = rst
    val Lm: LiveData<Int> = lm
    val Lcm: LiveData<Int> = lcm
    val Cm: LiveData<Int> = cm
    val Cdm: LiveData<Int> = cdm
    val Rcm: LiveData<Int> = rcm
    val Rm: LiveData<Int> = rm
    val Lfb: LiveData<Int> = lfb
    val Lcb: LiveData<Int> = lcb
    val Cb: LiveData<Int> = cb
    val Rcb: LiveData<Int> = rcb
    val Rfb: LiveData<Int> = rfb
    val Gk: LiveData<Int> = gk

    fun setPositionId(posi: Int) {
        this.positionId = posi
        positionId_check = posi
    }

    fun setPlayerId(pId: Int) {
        this.playerId = pId
        playerId_check = pId

        Log.d("TEST", "viewmodel: $mapPositionPlayer")
    }

    fun select() {
        when (positionId) {
            1 -> {
                st.value = playerId
            }
            2 -> {
                lst.value = playerId
            }
            3 -> {
                rst.value = playerId
            }
            4 -> {
                lm.value = playerId
            }
            5 -> {
                lcm.value = playerId
            }
            6 -> {
                cm.value = playerId
            }
            7 -> {
                cdm.value = playerId
            }
            8 -> {
                rcm.value = playerId
            }
            9 -> {
                rm.value = playerId
            }
            10 -> {
                lfb.value = playerId
            }
            11 -> {
                lcb.value = playerId
            }
            12 -> {
                cb.value = playerId
            }
            13 -> {
                rcb.value = playerId
            }
            14 -> {
                rfb.value = playerId
            }
            15 -> {
                gk.value = playerId
            }
        }
    }

    fun updateItemx(
        itemPosition: Int,
        itemPlayer: Int

    ) {
        val updatedItem = getItemEntry(itemPosition, itemPlayer)
        updateItem(updatedItem)
        mapPositionPlayer[positionId] = playerId
    }

    private fun updateItem(item: EntityX) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    fun addNewItem() {
        val newItem = getItemEntry(positionId, playerId)
        insertItem(newItem)
        mapPositionPlayer[positionId] = playerId
      }

    private fun insertItem(item: EntityX) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getItemEntry(itemPosition: Int, itemPlayer: Int): EntityX {
        return EntityX(
            itemPosition = itemPosition,
            itemPlayer = itemPlayer
        )
    }
}

class LineupViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LineupViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return LineupViewModel(itemDao)as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}