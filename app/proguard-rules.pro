-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.support.v4.app.DialogFragment
-keep public class * extends com.actionbarsherlock.app.SherlockListFragment
-keep public class * extends com.actionbarsherlock.app.SherlockFragment
-keep public class * extends com.actionbarsherlock.app.SherlockFragmentActivity
-keep public class * extends android.app.Fragment
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keep class com.firebase.** { *; }
-keep class org.apache.** { *; }
-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }
-dontwarn org.w3c.dom.**
-dontwarn org.joda.time.**
-dontwarn org.shaded.apache.**
-dontwarn org.ietf.jgss.**
-keepattributes EnclosingMethod
-keepattributes InnerClasses

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keep class io.fabric.sdk.android.services.events.EventsStorageListener
-keep class io.fabric.sdk.android.services.events.EventsStorage
-keep class io.fabric.sdk.android.services.common.CurrentTimeProvider
-keep class io.fabric.sdk.android.services.events.EventTransform
-keep class io.fabric.sdk.android.services.concurrency.Task
-keep class io.fabric.sdk.android.services.network.HttpMethod
-keep class io.fabric.sdk.android.services.network.HttpRequestFactory
-keep class io.fabric.sdk.android.Kit
-keep class io.fabric.sdk.android.services.common.IdManager
-keep class io.fabric.sdk.android.InitializationCallback
-keep class io.fabric.sdk.android.Fabric
-keep class io.fabric.sdk.android.services.common.IdManager$DeviceIdentifierType
-keep class io.fabric.sdk.android.services.persistence.PreferenceStore
-keep class io.fabric.sdk.android.services.persistence.FileStore
-keep class io.fabric.sdk.android.services.settings.PromptSettingsData
-keep class io.fabric.sdk.android.services.network.HttpRequest
-keep class io.fabric.sdk.android.services.settings.SessionSettingsData
-keep class io.fabric.sdk.android.services.settings.SettingsData
-keep class io.fabric.sdk.android.services.settings.BetaSettingsData
-keep class io.fabric.sdk.android.ActivityLifecycleManager
-keep class io.fabric.sdk.android.services.settings.AnalyticsSettingsData
-keep class io.fabric.sdk.android.services.concurrency.internal.RetryState
-keep class io.fabric.sdk.android.services.concurrency.internal.Backoff
-keep class io.fabric.sdk.android.services.common.Crash$LoggedException
-keep class io.fabric.sdk.android.services.common.Crash$FatalException

#video_compressing
-dontwarn com.googlecode.mp4parser.**

-keep class com.android.internal.telephony** { *; }
-keep class com.persianswitch.sdk.api** {
    *;
}

-dontwarn com.persianswitch.sdk.api.**
-keep public class com.persianswitch.okhttp3.**
-dontwarn com.persianswitch.okio.**

-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepattributes Signature
-keepattributes Annotation
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

-keep public class com.shahrdari.models.** { *; }
#-keep class com.sanatyar.investam.CustomViews.backboard.** { *; }
#-keep class com.sanatyar.investam.Classes.location.** { *; }
#-keep class com.sanatyar.investam.CustomViews.videocompressor.** { *; }
-keep class com.android.vending.billing


-keep class com.ontbee.legacyforks.cn.pedant.SweetAlert.Rotate3dAnimation {
    public <init>(...);
 }
-keep class  cn.pedant.SweetAlert** { *; }


################################################################
# Retrofit
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement




# Dagger ProGuard rules.
# https://github.com/square/dagger

-dontwarn dagger.internal.codegen.**
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection


#///////////EventBus//////////////
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# com.mobsandgeeks
-keep class com.mobsandgeeks.saripaar.** {*;}
-keep @com.mobsandgeeks.saripaar.annotation.ValidateUsing class * {*;}

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# for DexGuard only
#-keepresourcexmlelements manifest/application/meta-data@value=GlideModule


#-keep public class * implements androidx.versionedparcelable.VersionedParcelable