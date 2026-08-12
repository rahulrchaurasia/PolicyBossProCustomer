package com.policyboss.customer.navigation



import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

/**
 * Checks whether the current destination belongs to a graph.
 */
fun NavDestination?.isInGraph(graph: Dest): Boolean {

    return this?.hierarchy?.any {

        it.route == graph::class.qualifiedName

    } == true
}