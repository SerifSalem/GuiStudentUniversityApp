package qho543.guistudentuniversityapp// Class representing a university that manages students
class University {

    val studentList = mutableListOf<Student>()  // Stores a list of all students

    // Adds a new student to the list
    fun addStudent(s: Student) {
        studentList.add(s)
    }

    // Searches for a student by ID and returns it (or null if not found)
    fun findStudentById(id: String): Student? {
        for (s in studentList) {
            if (s.id == id) {
                return s  // Return the matching student
            }
        }
        return null  // Return null if no match found
    }

    // Returns a list of students enrolled in a specific course
    fun findStudentsByCourse(course: String): List<Student> {
        val matches = mutableListOf<Student>()  // Temporary list to store matches
        for (s in studentList) {
            if (s.course == course) {
                matches.add(s)  // Add matching student to list
            }
        }
        return matches  // Return all matching students
    }
}