package ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FridgeViewModel : ViewModel() {
    val topDoorOpen = MutableLiveData(false)
    val middleDoorOpen = MutableLiveData(false)
    val bottomDoorOpen = MutableLiveData(false)
}
