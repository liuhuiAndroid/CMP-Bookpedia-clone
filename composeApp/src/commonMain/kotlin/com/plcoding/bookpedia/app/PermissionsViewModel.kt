package com.plcoding.bookpedia.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.location.LOCATION
import kotlinx.coroutines.launch

class PermissionsViewModel(
    private val controller: PermissionsController,
) : ViewModel() {

    var permissionsState by mutableStateOf(PermissionState.NotDetermined)
        private set

    init {
        viewModelScope.launch {
            permissionsState = controller.getPermissionState(Permission.LOCATION)
        }
    }

    fun provideOrRequestLocationPermission() {
        viewModelScope.launch {
            try {
                controller.providePermission(Permission.LOCATION)
                permissionsState = PermissionState.Granted
            } catch (e: DeniedAlwaysException) {
                permissionsState = PermissionState.DeniedAlways
            } catch (e: DeniedException) {
                permissionsState = PermissionState.Denied
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }
}