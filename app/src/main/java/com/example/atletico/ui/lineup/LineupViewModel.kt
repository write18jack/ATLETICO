package com.example.atletico.ui.lineup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LineupViewModel : ViewModel() {

    private var positionId: Int = 0
    private var playerId: Int = 0

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
    }

    fun setPlayerId(pId: Int) {
        this.playerId = pId
    }

    fun select() {
        when (positionId) {
            1->{ st.value = playerId }
            2->{ lst.value = playerId }
            3->{ rst.value = playerId }
            4->{ lm.value = playerId }
            5->{ lcm.value = playerId }
            6->{ cm.value = playerId }
            7->{cdm.value = playerId }
            8->{ rcm.value = playerId }
            9->{ rm.value = playerId }
            10->{ lfb.value = playerId }
            11->{ lcb.value = playerId }
            12->{ cb.value = playerId }
            13->{ rcb.value = playerId }
            14->{ rfb.value = playerId }
            15->{ gk.value = playerId }
        }
    }
}