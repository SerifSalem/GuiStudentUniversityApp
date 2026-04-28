package qho543.guistudentuniversityapp// Abstract base class representing a general student
abstract class Student(val id: String, var name: String, var course: String) {

    var mark = 0  // Stores the student's mark (default is 0)
        set(newMark) {
            if (newMark in 0..100) {  // Ensures mark is within valid range (0–100)
                field = newMark      // Updates the mark only if valid
            }
        }

    // Returns a formatted string describing the student
    override fun toString(): String {
        return "$name, ID $id, is on $course, mark is $mark which is a grade of ${getGrade()}"
    }

    // Abstract method to calculate grade (implemented in subclasses)
    abstract fun getGrade(): String
}
