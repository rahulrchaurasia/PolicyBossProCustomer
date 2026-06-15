# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

##############################################
# 🔹 General Rules (REQUIRED)
##############################################

# Annotations & generics (important for Retrofit, Hilt, Serialization)
-keepattributes *Annotation*, Signature, InnerClasses, EnclosingMethod

# Better crash logs
-keepattributes SourceFile, LineNumberTable

# Kotlin metadata
-keep class kotlin.Metadata { *; }

##############################################
# 🔹 Hilt / Dagger (SAFE + MINIMAL)
##############################################

# Core Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep generated Hilt components
-keep class * implements dagger.hilt.internal.GeneratedComponent
-keep class * implements dagger.hilt.internal.GeneratedComponentManager

##############################################
# 🔹 Retrofit
##############################################

# Keep API interfaces
-keep @retrofit2.http.* interface * { *; }

# Ignore warnings
-dontwarn retrofit2.**

##############################################
# 🔹 OkHttp
##############################################

-dontwarn okhttp3.**
-dontwarn okio.**

##############################################
# 🔹 Kotlinx Serialization (IMPORTANT FIX)
##############################################

# Keep serializable classes
-keep @kotlinx.serialization.Serializable class * { *; }

# Keep generated serializer objects (THIS is the key part you mentioned)
-keepclassmembers class * {
    *** Companion;
    *** $serializer;
}

##############################################
# 🔹 ViewModel (Safe)
##############################################

-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    public <init>(...);
}

##############################################
# 🔹 Project Specific (ONLY if needed)
##############################################

# Example — keep only if you use reflection
# -keep class com.policyboss.** { *; }