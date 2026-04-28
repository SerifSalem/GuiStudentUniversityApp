package qho543.guistudentuniversityapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "University Student App",
    ) {
        App()
    }
}