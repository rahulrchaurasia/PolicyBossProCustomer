package com.policyboss.customer.feature.webView



import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.viewinterop.AndroidView
import com.policyboss.customer.ui.components.loading.AppLoadingOverlay
import com.policyboss.customer.ui.components.toolbarHeader.AppTopBar
import com.policyboss.customer.ui.theme.AppColors

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun CommonWebViewScreen(
    title: String,
    url: String,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoading by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White)
    ) {
        // 1. Move AppTopBar OUTSIDE the overlay so it is always visible and clickable
        AppTopBar(
            title = title,
            onBackClick = onBackClick,
            backIconTint = AppColors.TextPrimary,
            titleColor = AppColors.TextPrimary,
            trailingIcon = rememberVectorPainter(image = Icons.Default.Home),
            onTrailingClick = onHomeClick,
            trailingIconTint = AppColors.TextPrimary
        )

        // 2. Wrap ONLY the WebView in the overlay
        AppLoadingOverlay(
            isLoading = isLoading,
            message = "Loading page...",
            modifier = Modifier.weight(1f) // Takes up the remaining space below TopBar
        ) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        settings.domStorageEnabled = true

                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                                super.onPageStarted(view, url, favicon)
                                isLoading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                            }

                            // 3. Catch network errors to dismiss the loader
                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                isLoading = false
                            }
                        }

                        loadUrl(url)
                    }
                },
                update = { webView ->
                    if (webView.url != url) {
                        webView.loadUrl(url)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}