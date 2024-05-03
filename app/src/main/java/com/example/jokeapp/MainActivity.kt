package com.example.jokeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.jokeapp.databinding.ActivityMainBinding
import com.shakebugs.shake.LogLevel
import com.shakebugs.shake.Shake
import com.shakebugs.shake.ShakeScreen
import com.shakebugs.shake.actions.ShakeHomeAction
import com.shakebugs.shake.actions.ShakeHomeChatAction
import com.shakebugs.shake.actions.ShakeHomeSubmitAction
import com.shakebugs.shake.form.ShakeAttachments
import com.shakebugs.shake.form.ShakeEmail
import com.shakebugs.shake.form.ShakeInspectButton
import com.shakebugs.shake.form.ShakePicker
import com.shakebugs.shake.form.ShakePickerItem
import com.shakebugs.shake.form.ShakeTextInput
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

//        Shake.start(this, "yQiyY0TG6G5KjplRI8AAxeQXeDgAAiLkucmFCIQR", "SNf4X4fhRC5TIz5zb9VQOPiWDsEAZVwwi0I9DCxOyFZnGtNw1PSdmL4")
//        Shake.getReportConfiguration().isAutoVideoRecording = true
//        Shake.getReportConfiguration().isConsoleLogsEnabled = false
//        Shake.getReportConfiguration().isEnableActivityHistory = false
//        Shake.getReportConfiguration().isEnableBlackBox = false

//        startActivity(
//            Intent("android.settings." +
//                "ACTION_NOTIFICATION_LISTENER_SETTINGS")
//        )
//        Shake.log(LogLevel.INFO, "Log message goes here!")
//
//        Shake.show(ShakeScreen.NEW)
//        Shake.show(ShakeScreen.HOME)
//        Shake.show(ShakeScreen.CHAT)

//        Shake.handleNetworkRequest()



        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContentView(binding.root)
        binding.buttonid
        binding.textjoxid

        binding.buttonid.setOnClickListener {
            Timber.e("Fetch joke button clicked")
            viewModel.fetchJoke()
        }

        addObservers()
//        openRoadmap()
//        onChatRoomJoined(Room)
    }
//    fun onChatRoomJoined(room: Room) {
//        Shake.setMetadata("roomId", room.MASTER_TABLE_NAME)
//    }
//        @SuppressLint("SuspiciousIndentation")
//        fun openRoadmap() {
//        val customAction = ShakeHomeAction(
//            title = R.string.app_name,
//            subtitle = R.string.app_name,
//            icon = R.drawable.img,
//            handler = {
//            }
//        )
//        val submitAction = ShakeHomeSubmitAction(
//            title = R.string.app_name,
//            subtitle = R.string.a,
//            icon = R.drawable.ic_launcher_background,
//        )
//        val chatAction = ShakeHomeChatAction(
//            title = R.string.ap,
//            subtitle = R.string.app,
//            icon = R.drawable.ic_launcher_foreground,
//        )
//        val description = ShakeTextInput(key = "Steps to reproduce", label = R.string.app, initialValue = "", required = false)
//        val email = ShakeEmail(key = "Email to contact you on", label = R.string.ap, initialValue = "", required = true)
//        val item = ShakePickerItem(key = "Playbox Mini", text = R.string.a, icon = R.drawable.img, tag = "playbox-mini")
//
//        val picker = ShakePicker(key = "Choose your console", label = R.string.ap, items = arrayListOf(item))
//        val inspectButton = ShakeInspectButton()
//        val attachments = ShakeAttachments()
//
//        val actions = arrayListOf(customAction, submitAction, chatAction)
//        Shake.getReportConfiguration().homeActions = actions
//            Shake.insertNotificationEvent(
//                NOTIFICATION_SERVICE,
//                "yQiyY0TG6G5KjplRI8AAxeQXeDgAAiLkucmFCIQR",
//            )
//    }
    private fun addObservers() {
        viewModel.response.observe(this) { response ->
            if (response != null) {
                binding.textjoxid.text = response.fact
                binding.textlength.text = response.length.toString()
                Timber.d("fact fetched: ${response.fact}, Length: ${response.length}")
            } else {
                Timber.e("Response is null")
            }
        }
    }
}