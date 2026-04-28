package qho543.guistudentuniversityapp// Subclass representing an undergraduate student
class Undergraduate(id: String, name: String, course: String) : Student(id, name, course) {

    // Implements grade calculation based on mark ranges
    override fun getGrade(): String {
        return when (mark) {
            in 70..100 -> "First"   // Highest grade classification
            in 60..69 -> "2/1"      // Upper second class
            in 50..59 -> "2/2"      // Lower second class
            in 40..49 -> "Third"    // Third class
            else -> "Fail"          // Below 40 is a fail
        }
    }
}