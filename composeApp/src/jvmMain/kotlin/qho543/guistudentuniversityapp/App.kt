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
fun App() {

    val university = remember { University() }

    val studentList = remember {
        mutableStateListOf<Student>()
    }

    MaterialTheme {
        Column {

            AddStudent(onStudentAdded = { student ->

                university.addStudent(student)

                studentList.clear()
                studentList.addAll(university.studentList)
            })

            StudentList(studentList)
        }
    }
}

@Composable
fun AddStudent(onStudentAdded: (Student) -> Unit) {

    val idState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val courseState = remember { mutableStateOf("") }
    val markState = remember { mutableStateOf("") }

    Column {
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

        Button(
            onClick = {
                val student = Undergraduate(
                    idState.value,
                    nameState.value,
                    courseState.value
                )

                student.mark = markState.value.toIntOrNull() ?: 0

                onStudentAdded(student)

                idState.value = ""
                nameState.value = ""
                courseState.value = ""
                markState.value = ""
            }
        ) {
            Text("Add Student")
        }
    }
}

@Composable
fun StudentList(students: List<Student>) {
    Column {
        students.forEach {
            Text(it.toString())
        }
    }
}

