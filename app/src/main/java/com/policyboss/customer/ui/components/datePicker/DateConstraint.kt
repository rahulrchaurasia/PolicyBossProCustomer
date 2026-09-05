package com.policyboss.customer.ui.components.datePicker



import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import java.util.Calendar
import java.util.TimeZone

// 1. Cleaned up, non-redundant constraints
sealed class DateConstraint {
    data object Any : DateConstraint()

    data object PastOrToday : DateConstraint()
    data object FutureOrToday : DateConstraint() // Renamed to keep pattern consistent

    data object TodayOnly : DateConstraint()

    data object PastOnly : DateConstraint()      // Replaces "YesterdayOrBefore" (shorter)
    data object FutureOnly : DateConstraint()    // Replaces "TomorrowOrAfter" (shorter)

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

// 2. Optimized M3 SelectableDates Converter
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

    // Helper function to easily calculate offsets (days, months, years)
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
            maxTime = offset(Calendar.DAY_OF_YEAR, -1)
        }
        is DateConstraint.FutureOnly -> {
            minTime = offset(Calendar.DAY_OF_YEAR, 1)
            maxTime = Long.MAX_VALUE
        }
        is DateConstraint.PastDays -> {
            minTime = offset(Calendar.DAY_OF_YEAR, -days)
            maxTime = today
        }
        is DateConstraint.FutureDays -> {
            minTime = today
            maxTime = offset(Calendar.DAY_OF_YEAR, days)
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

    // 3. Return a highly-optimized checker
    return object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            // Because we pre-calculated the min and max, this function does ZERO math.
            // It just checks if the date falls in the range. Super fast UI scrolling!
            return utcTimeMillis in minTime..maxTime
        }
    }
}