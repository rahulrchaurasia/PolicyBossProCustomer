package com.policyboss.customer.utils.permission


// Android core

// Compose core

// Compose UI

// Compose Material3

// Activity result API (for rememberPermissionHandler)

// Permissions helpers

// Your custom classes

// -------------------- ANDROID --------------------


// -------------------- COMPOSE CORE --------------------

// -------------------- COMPOSE UI --------------------

// -------------------- MATERIAL 3 --------------------

// -------------------- YOUR HELPERS --------------------
// (Make sure these exist in your project)


// -----------------------------------------------------


//@Composable
//fun CameraPermissionScreen(
//    onPermissionGranted: () -> Unit = {}
//) {
//    val context = LocalContext.current
//    val activity = findActivity()
//
//    var showRationale by remember { mutableStateOf(false) }
//    var showDeniedDialog by remember { mutableStateOf(false) }
//
//    val permissions = arrayOf(Manifest.permission.CAMERA)
//
//    val requestPermission = rememberPermissionHandler(
//        onResult = { granted ->
//            if (granted) {
//                onPermissionGranted()
//            }
//        },
//        onPermanentlyDenied = {
//            showDeniedDialog = true
//        }
//    )
//
//    val checkAndRequest = remember {
//        {
//            when {
//                permissions.all {
//                    ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
//                } -> {
//                    onPermissionGranted()
//                }
//
//                permissions.any {
//                    activity?.shouldShowRequestPermissionRationale(it) == true
//                } -> {
//                    showRationale = true
//                }
//
//                else -> {
//                    requestPermission(permissions)
//                }
//            }
//        }
//    }
//
//    Button(onClick = checkAndRequest) {
//        Text("Request Camera Permission")
//    }
//
//    // Rationale Dialog
//    if (showRationale) {
//        PermissionRationaleDialog(
//            message = "Camera permission is required to capture photos.",
//            onGrant = {
//                showRationale = false
//                requestPermission(permissions)
//            },
//            onCancel = {
//                showRationale = false
//            }
//        )
//    }
//
//    // Permanently Denied Dialog
//    if (showDeniedDialog) {
//        PermissionDeniedDialog(
//            message = "Please enable camera permission from settings.",
//            onOpenSettings = {
//                showDeniedDialog = false
//                openAppSettings(context)
//            },
//            onCancel = {
//                showDeniedDialog = false
//            }
//        )
//    }
//
//    // Re-check when returning from Settings
//    LaunchedEffect(Unit) {
//        snapshotFlow {
//            permissions.all {
//                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
//            }
//        }.collect { granted ->
//            if (granted) {
//                onPermissionGranted()
//            }
//        }
//    }
//}