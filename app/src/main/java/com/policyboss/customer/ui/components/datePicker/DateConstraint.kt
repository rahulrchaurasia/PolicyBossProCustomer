package com.policyboss.customer.ui.components.datePicker



import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.util.Calendar
import java.util.TimeZone

// 1. Cleaned up, non-redundant constraints


sealed class DateConstraint {
    data object Any : DateConstraint()
    data object PastOrToday : DateConstraint()
    data object FutureOrToday : DateConstraint()
    data object TodayOnly : DateConstraint()
    data object PastOnly : DateConstraint()
    data object FutureOnly : DateConstraint()
    data class PastDays(val days: Int) : DateConstraint()
    data class FutureDays(val days: Int) : DateConstraint()
    data class PastMonths(val months: Int) : DateConstraint()
    data class FutureMonths(val months: Int) : DateConstraint()
    data class PastYears(val years: Int) : DateConstraint()
    data class FutureYears(val years: Int) : DateConstraint()
    data class AtLeastAge(val years: Int) : DateConstraint()
    data class Between(
        val startDateMillis: Long,
        val endDateMillis: Long
    ) : DateConstraint()
}

@OptIn(ExperimentalMaterial3Api::class)
fun DateConstraint.toSelectableDates(): SelectableDates {

    // 1. Get today's UTC midnight exactly ONCE
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    val today = calendar.timeInMillis

    // Helper function to easily calculate offsets
    fun offset(field: Int, amount: Int): Long {
        val tempCal = calendar.clone() as Calendar
        tempCal.add(field, amount)
        return tempCal.timeInMillis
    }

    // 2. Pre-calculate the exact bounds based on the constraint
    val minTime: Long
    val maxTime: Long

    when (this) {
        is DateConstraint.Any -> {
            minTime = Long.MIN_VALUE
            maxTime = Long.MAX_VALUE
        }
        is DateConstraint.PastOrToday -> {
            minTime = Long.MIN_VALUE
            maxTime = today
        }
        is DateConstraint.FutureOrToday -> {
            minTime = today
            maxTime = Long.MAX_VALUE
        }
        is DateConstraint.TodayOnly -> {
            minTime = today
            maxTime = today
        }
        is DateConstraint.PastOnly -> {
            minTime = Long.MIN_VALUE
            maxTime = offset(Calendar.DATE, -1) // Used DATE instead of DAY_OF_YEAR
        }
        is DateConstraint.FutureOnly -> {
            minTime = offset(Calendar.DATE, 1)
            maxTime = Long.MAX_VALUE
        }
        is DateConstraint.PastDays -> {
            minTime = offset(Calendar.DATE, -days)
            maxTime = today
        }
        is DateConstraint.FutureDays -> {
            minTime = today
            maxTime = offset(Calendar.DATE, days)
        }
        is DateConstraint.PastMonths -> {
            minTime = offset(Calendar.MONTH, -months)
            maxTime = today
        }
        is DateConstraint.FutureMonths -> {
            minTime = today
            maxTime = offset(Calendar.MONTH, months)
        }
        is DateConstraint.PastYears -> {
            minTime = offset(Calendar.YEAR, -years)
            maxTime = today
        }
        is DateConstraint.FutureYears -> {
            minTime = today
            maxTime = offset(Calendar.YEAR, years)
        }
        is DateConstraint.AtLeastAge -> {
            minTime = Long.MIN_VALUE
            maxTime = offset(Calendar.YEAR, -years)
        }
        is DateConstraint.Between -> {
            minTime = startDateMillis
            maxTime = endDateMillis
        }
    }

    // 👇 NEW: Extract min and max years for the Year Picker dropdown
    val minYear = if (minTime == Long.MIN_VALUE) Int.MIN_VALUE else {
        Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = minTime }.get(Calendar.YEAR)
    }
    val maxYear = if (maxTime == Long.MAX_VALUE) Int.MAX_VALUE else {
        Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply { timeInMillis = maxTime }.get(Calendar.YEAR)
    }

    // 3. Return a highly-optimized checker
    return object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return utcTimeMillis in minTime..maxTime
        }

        // 👇 NEW: Prevents user from scrolling to invalid years in the year dropdown
        override fun isSelectableYear(year: Int): Boolean {
            return year in minYear..maxYear
        }
    }
}

// Add this at the bottom of DateConstraint.kt
fun DateConstraint.getInitialDisplayedMonthMillis(): Long? {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    return when (this) {
        is DateConstraint.AtLeastAge -> {
            calendar.add(Calendar.YEAR, -years)
            calendar.timeInMillis
        }
        is DateConstraint.PastOnly -> {
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            calendar.timeInMillis
        }
        is DateConstraint.Between -> {
            endDateMillis
        }
        else -> null // Null means it will just open to the current month/year
    }
}