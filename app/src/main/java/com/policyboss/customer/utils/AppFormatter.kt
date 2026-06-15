package com.policyboss.customer.utils

object AppFormatter {

    fun maskMobile(number: String): String {

        return if (number.length >= 10) {

            "+91 ${number.take(5)}*****"

        } else {
            number
        }
    }
}