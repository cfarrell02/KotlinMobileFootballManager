package org.setu.model

import java.time.LocalDate

class Staff(
    name: String,
    dateOfBirth: LocalDate,
    nationality: String,
    var role: String,
    var salary: Double
) : Person(name,dateOfBirth,nationality)
{
    init {
        // Validation here
        require(role.isNotBlank()) { "Role cannot be blank" }
        require(salary > 0) { "Salary must be greater than 0" }
    }

    override fun toString(): String {
        return "Staff: $name, $dateOfBirth, $nationality, $role, $salary"
    }
}