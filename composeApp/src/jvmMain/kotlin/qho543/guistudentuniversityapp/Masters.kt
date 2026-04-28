package qho543.guistudentuniversityapp// Subclass representing a postgraduate (Masters) student
class Masters(id: String, name: String, course: String) : Student(id, name, course) {

    // Implements grade calculation based on mark ranges
    override fun getGrade(): String {
        return when (mark) {
            in 70..100 -> "Distinction"  // Highest postgraduate grade
            in 50..69 -> "Merit"         // Mid-level postgraduate grade
            in 40..49 -> "Pass"          // Minimum passing grade
            else -> "Fail"               // Below 40 is a fail
        }
    }
}