package com.example.coroutineswithtests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//isDispatcher is set to be able to use testCoroutineDispatcher from a test, if not Dispatchers.IO is assigned by default
class MainViewModel(private val isDispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

    private val _loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val loginResult: LiveData<Boolean> get() = _loginResult

    fun onSubmitClicked(user: String, pass: String) {
        viewModelScope.launch {
            _loginResult.value = withContext(isDispatcher) { validateLogin(user, pass) }
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        Thread.sleep(2000)
        return username.isNotEmpty() && password.isNotEmpty()
    }
}