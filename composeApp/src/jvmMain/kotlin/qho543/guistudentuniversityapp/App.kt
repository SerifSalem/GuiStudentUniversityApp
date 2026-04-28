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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

@Composable
fun App() {
    val university = remember { University() }
    val studentList = remember { mutableStateListOf<Student>() }

    MaterialTheme {
        Column {
            AddStudent(onStudentAdded = { student ->
                university.addStudent(student)
                studentList.clear()
                studentList.addAll(university.studentList)
            })
            // Composable to search students by course using callback
            SearchByCourse(onCourseEntered = { course ->
                // Get filtered students from University
                val results = university.findStudentsByCourse(course)
                studentList.clear()  // Clear current UI list
                studentList.addAll(results)  // Update UI with filtered results
            })

            // Display the current list of students (either full list or filtered results)
            StudentList(studentList)
        }
    }
}

@Composable
fun StudentTypeDropdown(onTypeSelected: (String) -> Unit) {

    val expanded = remember { mutableStateOf(false) }  // Controls dropdown visibility

    Column {

        Button(onClick = {
            expanded.value = !expanded.value  // Toggle dropdown visibility
        }) {
            Text("Select Student Type")
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }  // Close when clicking outside
        ) {

            DropdownMenuItem(
                text = { Text("Undergraduate") },
                onClick = {
                    onTypeSelected("u")  // Send selection back
                    expanded.value = false
                }
            )

            DropdownMenuItem(
                text = { Text("Masters") },
                onClick = {
                    onTypeSelected("m")  // Send selection back
                    expanded.value = false
                }
            )
        }
    }
}

@Composable
fun AddStudent(onStudentAdded: (Student) -> Unit) {

    val idState = remember { mutableStateOf("") }
    val nameState = remember { mutableStateOf("") }
    val courseState = remember { mutableStateOf("") }
    val markState = remember { mutableStateOf("") }
    val typeState = remember { mutableStateOf("u") }  // Default type (Exercise 7)
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

        // Dropdown for selecting type (Exercise 7)
        StudentTypeDropdown(onTypeSelected = {
            typeState.value = it
        })

        Text(
            text = "Selected type: ${
                if (typeState.value == "m") "Masters" else "Undergraduate"
            }"
        )

        Button(
            onClick = {
                // Updated code for Excercise 7 to create Undergraduate or Master Student object
                val student =
                    if (typeState.value == "m")
                        Masters(idState.value, nameState.value, courseState.value)
                    else
                        Undergraduate(idState.value, nameState.value, courseState.value)

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

@Composable
fun SearchByCourse(onCourseEntered: (String) -> Unit) {
    // Stores the user input for course
    val courseState = remember { mutableStateOf("") }

    Column {
        Text("Enter course")  // Label for the input section

        OutlinedTextField(
            value = courseState.value,  // Current value of the TextField
            singleLine = true,  // Restricts input to a single line
            onValueChange = { courseState.value = it },  // Updates state when user types
            label = { Text("Course") }  // Placeholder/label inside the TextField
        )

        Button(
            onClick = {
                // Sends entered course back to App via callback
                onCourseEntered(courseState.value)
            }
        ) {
            Text("Search by Course")  // Button label
        }
    }
}