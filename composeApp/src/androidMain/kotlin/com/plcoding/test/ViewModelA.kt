package com.plcoding.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

// https://www.youtube.com/watch?v=ZTebNp-FyYY
class ViewModelA : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    val isEmailValid = email.map {
        it.isValidEmail()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val isPasswordValid = password.map {
        it.isValidPassword()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    val canRegister = combine(
        isEmailValid,
        isPasswordValid
    ) { isEmailValid, isPasswordValid ->
        isEmailValid && isPasswordValid
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun updateEmail(email: String) {
        _email.update { email }
    }
}

fun String.isValidEmail(): Boolean {
    return true
}

fun String.isValidPassword(): Boolean {
    return true
}