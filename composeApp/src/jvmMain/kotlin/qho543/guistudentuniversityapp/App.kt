package qho543.guistudentuniversityapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

@Composable
fun App() {

    // SnapshotStateList to store students
    val students = remember {
        mutableStateListOf<Student>()
    }

    // For now, just an empty UI container
    MaterialTheme {
        // UI will be added in next exercises
    }
}


