package com.example.atletico.ui.lineup

import android.content.ClipData
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class SaveLineUpViewModel(private val itemDao: ItemDao): ViewModel() {

    val allItems: LiveData<List<Entity>> = itemDao.getItems().asLiveData()

    fun updateItem(
        itemId: Int,
        itemPosition: Int,
        itemPlayer: Int
    ) {
        val updatedItem = getUpdatedItemEntry(itemId, itemPosition, itemPlayer)
        updateItem(updatedItem)
    }

    private fun updateItem(item: Entity) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    fun addNewItem(itemPosition: Int, itemPlayer: Int) {
        val newItem = getNewItemEntry(itemPosition, itemPlayer)
        insertItem(newItem)
    }

    private fun insertItem(item: Entity) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    fun deleteItem(item: Entity) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun retrieveItem(id: Int): LiveData<Entity> {
        return itemDao.getItem(id).asLiveData()
    }

    fun isEntryValid(itemPosition: Int, itemPlayer: Int): Boolean {
        if (itemPosition.toString().isBlank() || itemPlayer.toString().isBlank()) {
            return false
        }
        return true
    }

    private fun getNewItemEntry(itemPosition: Int, itemPlayer: Int): Entity {
        return Entity(
            itemPosition = itemPosition,
            itemPlayer = itemPlayer
        )
    }

    private fun getUpdatedItemEntry(
        itemId: Int,
        itemPosition: Int,
        itemPlayer: Int
    ): Entity {
        return Entity(
            id = itemId,
            itemPosition = itemPosition,
            itemPlayer = itemPlayer
        )
    }
}
class SaveLineupViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SaveLineUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SaveLineUpViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
