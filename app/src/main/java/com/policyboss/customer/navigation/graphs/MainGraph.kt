package com.policyboss.customer.navigation.graphs

import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.policyboss.customer.feature.home.component.home.vaultSection.component.EmptyVaultState
import com.policyboss.customer.feature.home.ui.HomeRoute
import com.policyboss.customer.feature.home.ui.tabScreen.CartScreen
import com.policyboss.customer.feature.home.ui.tabScreen.ProfileScreen
import com.policyboss.customer.feature.home.ui.tabScreen.WishListScreen
import com.policyboss.customer.feature.joinPrivilege.ui.JoinPrivilegeScreen
import com.policyboss.customer.navigation.AppNavigator
import com.policyboss.customer.navigation.Dest
import com.policyboss.customer.feature.mainScreen.MainScreen

//import com.interstellar.rahulpihujetpackdemo.rootGraph.graph.route

/*
   ***************************************************


    *
    * RootNavGraph
 ├── AuthGraph
 └── MainGraph
        ├── MainHome (Bottom Tabs Container)
        ├── HomeGraph
        ├── ProductGraph
        ├── CartGraph
        └── CarGraph
    ***************************************************

  *
  * ✅ 🧠 GOLDEN RULE (FINAL)
        Usage	                 What to use
        composable	              composable<Dest.Home> ✅
        navigate()	              navigate(Dest.Home) ✅
       startDestination	          Dest.Home.route() ✅
        navigation(route=...)	   "graph_name" string ✅
 */

//2️⃣ MAIN APP MODULE
//this for main App Module
//Note: we diveded graph in 3 module

// NavGraph is the actual map that says where each screen lives:


 /*

 🧠 INDUSTRY PATTERN (What you are doing now)
✔ Structure:
RootNavGraph
 ├── Splash
 ├── AuthGraph (optional navigation{})
 └── MainGraph (FLAT)
       ├── MainHome (UI container)
       ├── Tabs
       │     ├── Home
       │     ├── WishList
       │     ├── Cart
       │     └── Profile
       │
       ├── homeGraph()   // products flow
       ├── CheckoutGraph()   // firther paymet and receipt Flow
       ├── carGraph()    // multi-step → can use navigation{}
 ✔ Tabs (must be in MainGraph)
composable<Dest.Home> { HomeScreen() }
composable<Dest.WishList> { WishListScreen() }
composable<Dest.Cart> { TransactionScreen() }
composable<Dest.Profile> { ProfileScreen() }

  */

/*
////// Vote :-VVIMP   we just separate graph in 3 module but not created nested graph   ////////////////////////////

👉 This is modularization, NOT navigation graph

✔ Feature Flow (separated but NOT graph)
fun homeGraph() {
    composable<Dest.Products> { ... }
    composable<Dest.ProductDetail> { ... }
}
 */

/*
Note :
4. Your Statement (Verification)

“MainGraph handles all navigation, and we split into child functions, not graphs”

👉 ✔ 100% CORRECT

This is exactly how most production apps are built.
 */


/**********************  Base Graph Flow Concept *****************************/
/*

✅ What is a “Feature Graph” in YOUR project?

👉 A feature graph = a group of related screens (a flow / journey)

NOT necessarily something written using navigation {}.

🔍 In YOUR current project
🔴 These are ROOT / FLOW graphs

These represent top-level app flows:

RootNavGraph
 ├── authGraph   ✅ (Login/Register flow)
 └── mainGraph   ✅ (Main app flow)

👉 These are NOT feature graphs
👉 These are APP ENTRY FLOWS

🟢 These are your Feature Graphs

These are inside mainGraph:

1. Home Feature
homeGraph()

Flow:

Home → Products → ProductDetail
2. Checkout Feature
checkoutGraph()   // (your cartGraph renamed)

Flow:

Cart → CartDetail → Receipt
3. Car Feature
carGraph()

Flow:

CarInsurance → CarJourney
🧠 KEY UNDERSTANDING
Type	Example	Purpose
Root Graph	authGraph, mainGraph	App-level flow switching
Feature Graph	homeGraph, checkoutGraph, carGraph	Business logic journeys
Destination	Home, ProductDetail	Single screen
❗ IMPORTANT CLARIFICATION

“Feature graph ≠ must use navigation{}”

Means:

You can define feature graph in 2 ways

✅ OPTION 1 (YOUR CURRENT — GOOD)

No nested graph, just grouping:

fun NavGraphBuilder.homeGraph() {
    composable<Dest.Products> { ... }
    composable<Dest.ProductDetail> { ... }
}

✔ Simple
✔ Clean
✔ Recommended for small–medium apps

✅ OPTION 2 (WHEN FLOW IS BIG)
navigation<Dest.HomeGraph>(
    startDestination = Dest.Products
) {
    composable<Dest.Products> { ... }
    composable<Dest.ProductDetail> { ... }
}

✔ Needed when:

deep navigation
multiple entry points
independent back stack
🔥 YOUR CURRENT SETUP (FINAL CLARITY)
✔ Root level
RootNavGraph
✔ Flow level
authGraph
mainGraph
✔ Feature level (inside mainGraph)
homeGraph
checkoutGraph
carGraph
🚨 WHEN SHOULD YOU USE navigation {}?

Only when:

✅ Case 1: Independent flow

Example:

Checkout flow
Step1 → Step2 → Payment → Success
✅ Case 2: You want separate backstack

Example:

Bottom tabs with independent history
✅ Case 3: Deep linking into sub-flow
❌ WHEN NOT NEEDED (YOUR CURRENT CASE)

Your flows:

Products → Detail → Cart → Receipt

👉 Linear flow
👉 No need separate graph

✔ Your current approach is correct

💡 SIMPLE RULE (REMEMBER THIS)

If your flow is just navigation chain → DON'T use navigation{}

If your flow is complex / independent → USE navigation{}

🎯 FINAL ANSWER

👉 authGraph & mainGraph = NOT feature graphs
👉 They are app-level graphs

👉 homeGraph, cartGraph, carGraph = Feature graphs
 */


fun NavGraphBuilder.mainGraph(
    navigator: AppNavigator
) {
    // ✅ Nested Graph using the Object as the Route
    navigation<Dest.MainGraph>(
        startDestination = Dest.MainScreen
    ) {


        // ✅ Main shell (NO currentRoute, NO tab navigation)
        composable<Dest.MainScreen> {
            MainScreen(
                globalActions = navigator
            )
        }

        // ✅ Register your NEW Full Screen here!
        composable<Dest.JoinPrivilege> {
            JoinPrivilegeScreen(
                onBackClick = { navigator.navigateBack() }
            )
        }
        
        // 2. Add Bosspedia Here!
        composable<Dest.Bosspedia> {
            //BosspediaScreen()
            WishListScreen()
        }

        // Note: If you are managing tabs entirely manually inside TabContentHost using a `when`
        // statement, you actually MIGHT NOT need to declare the tabs here.
        // BUT, if you use `globalActions.navigateTo(Dest.Privilege)`, it must be registered.
        // 🟢 Tabs (MANDATORY)
        // ✅ REGISTER ALL TABS HERE

        composable<Dest.Vault> {
            CartScreen(
                )
        }

        composable<Dest.Privilege> {
            ProfileScreen(

            )
        }
//
    }
}


