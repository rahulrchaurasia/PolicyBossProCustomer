package com.policyboss.customer.feature.privilege.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.policyboss.customer.feature.privilege.privillageState.PrivilegeAction
import com.policyboss.customer.feature.privilege.privillageState.PrivilegeUiEvent
import com.policyboss.customer.feature.privilege.privillageState.PrivilegeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivilegeViewModel @Inject constructor(
    val player: ExoPlayer
) : ViewModel() {

    // 1. UI STATE: Holds the data the screen needs to draw itself.
    private val _uiState = MutableStateFlow(PrivilegeUiState())
    val uiState: StateFlow<PrivilegeUiState> = _uiState.asStateFlow()

    private val _uiEvent = Channel<PrivilegeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        // Automatically set up the video when the screen is opened
        initializeVideo()
    }

    private fun initializeVideo() {
        // Use a remote URL. Do not package large videos in the APK.
        // This is a standard Google test video URL.
        val remoteVideoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
        val mediaItem = MediaItem.fromUri(Uri.parse(remoteVideoUrl))

        player.setMediaItem(mediaItem)
        player.prepare()

        // Start playing automatically
        player.playWhenReady = true

        // Show the floating video player in the UI
        _uiState.update { it.copy(isFloatingVideoVisible = true) }
    }

    // Inside PrivilegeViewModel...

    fun onAction(action: PrivilegeAction) {
        when (action) {
            is PrivilegeAction.CloseFloatingVideo -> {
                _uiState.update { it.copy(isFloatingVideoVisible = false) }
                player.pause()
            }
            is PrivilegeAction.OnVideoClick -> {
                // 1. Pause the floating video so audio doesn't overlap
                player.pause()

                // 2. Hide the floating player (optional, depends on your desired UX)
                _uiState.update { it.copy(isFloatingVideoVisible = false) }

                // 3. Tell the Route to navigate to the full screen video
                viewModelScope.launch {
                    //Event trigger
                    _uiEvent.send(PrivilegeUiEvent.NavigateToFullScreenVideo)
                }
            }
            is PrivilegeAction.SetupAccountClicked -> {
                // Setup logic
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // CRITICAL: Always release the player to prevent severe memory leaks
        player.release()
    }

}