# =========================================
# General
# =========================================
# Preserve line numbers for better stack traces
-keepattributes SourceFile,LineNumberTable

# Hide original source file names
-renamesourcefileattribute SourceFile

# Keep Kotlin metadata (needed for reflection)
-keepclassmembers class kotlin.Metadata { *; }

# =========================================
# Hilt / Dagger
# =========================================
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.internal.GeneratedComponent {}
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory {}
-keep class com.example.brewbuddy.** { *; }

# =========================================
# Room
# =========================================
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep class * extends androidx.room.RoomDatabase

# =========================================
# Coroutines
# =========================================
-keepclassmembers class kotlinx.coroutines.debug.* { *; }

# =========================================
# Retrofit / Gson
# =========================================
-dontwarn okio.**
-dontwarn javax.annotation.**

# Keep Retrofit interfaces (annotations used via reflection)
-keepattributes Signature
-keepattributes *Annotation*

# Keep model classes used by Gson (reflection based)
-keep class com.example.brewbuddy.model.** { *; }

# =========================================
# Glide
# =========================================
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** { *; }

# Needed for Glide annotations
-keep @com.bumptech.glide.annotation.GlideModule class *
-keep @com.bumptech.glide.annotation.GlideExtension class *
-keepclasseswithmembers class * {
    @com.bumptech.glide.annotation.GlideOption <methods>;
}
-keepclasseswithmembers class * {
    @com.bumptech.glide.annotation.GlideType <methods>;
}

# =========================================
# OkHttp
# =========================================
-dontwarn okhttp3.**
