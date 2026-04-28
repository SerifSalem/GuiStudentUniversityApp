package qho543.guistudentuniversityapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun StudentList(students: List<Student>) {
    Column {
        students.forEach {
            Text(it.toString())
        }
    }
}

@Composable
fun App() {

    // State list of students (Exercise 1)
    val students = remember { mutableStateListOf<Student>() }
    // State variables for input fields (Exercise 2)
    val idState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val courseState = remember { mutableStateOf("") }
    val markState = remember { mutableStateOf("") }

    MaterialTheme {
        Column {

            // Input fields
            OutlinedTextField(
                value = idState.value,
                singleLine = true,
                onValueChange = { idState.value = it },
                label = { Text("Enter student ID") }
            )

            OutlinedTextField(
                value = nameState.value,
                singleLine = true,
                onValueChange = { nameState.value = it },
                label = { Text("Enter student name") }
            )

            OutlinedTextField(
                value = courseState.value,
                singleLine = true,
                onValueChange = { courseState.value = it },
                label = { Text("Enter course") }
            )

            OutlinedTextField(
                value = markState.value,
                singleLine = true,
                onValueChange = { markState.value = it },
                label = { Text("Enter mark") }
            )

            // Add student button
            Button(
                onClick = {
                    val student = Undergraduate(
                        idState.value,
                        nameState.value,
                        courseState.value
                    )

                    student.mark = markState.value.toIntOrNull() ?: 0
                    students.add(student)

                    // Clear inputs
                    idState.value = ""
                    nameState.value = ""
                    courseState.value = ""
                    markState.value = ""
                }
            ) {
                Text("Add Student")
            }

            // Display list (Exercise 3)
            StudentList(students)
        }
    }
}




