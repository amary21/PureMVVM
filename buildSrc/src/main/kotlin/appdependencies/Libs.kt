package appdependencies

import appdependencies.Versions.appCompatX
import appdependencies.Versions.appCoreX
import appdependencies.Versions.fastAdapterLib
import appdependencies.Versions.kotPref
import appdependencies.Versions.lifecycle
import appdependencies.Versions.savedstate
import appdependencies.Versions.retrofitLogging
import appdependencies.Versions.room

object Libs {
    object Core {
        const val appcompat = "androidx.appcompat:appcompat:$appCompatX"
        const val coreKtx = "androidx.core:core-ktx:$appCoreX"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.fragment}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.ui}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${lifecycle}"
        //const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle}"
        // view model saved state handler
        const val savedStateViewModel = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${savedstate}"
        // kotlin live data extensions
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle}"
        // alternately - if using Java8, use the following instead of lifecycle-compiler
        const val common = "androidx.lifecycle:lifecycle-common-java8:${lifecycle}"

    }

    ///------ RECYCLER VIEW ADAPTER
    object FastAdapter {
        const val core = "com.mikepenz:fastadapter:$fastAdapterLib@aar"
        const val ui = "com.mikepenz:fastadapter-extensions-ui:${fastAdapterLib}"
        const val diff = "com.mikepenz:fastadapter-extensions-diff:${fastAdapterLib}"
        const val uiExt = "com.mikepenz:fastadapter-extensions-ui:${fastAdapterLib}"
        const val expandable = "com.mikepenz:fastadapter-extensions-expandable:${fastAdapterLib}"
    }

    //--- ROOM DB
    object Room {
        const val runtime = "androidx.room:room-runtime:$room"
        const val ktx = "androidx.room:room-ktx:$room"
        const val kapt = "androidx.room:room-compiler:$room"
    }

    //------ HTTP
    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val logging = ("com.github.ihsanbal:LoggingInterceptor:${retrofitLogging}")
    }

    ///------ VIEWS
    object ImageLoading {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }

    object Common {
        ///--- View Pager Indicator
        const val viewPagerIndicator = "me.relex:circleindicator:${Versions.viewPagerIndicator}"

        //--- SHARED PREFERENCE
        const val kotpref = "com.chibatching.kotpref:kotpref:${kotPref}"

        // COROUTINES MANAGER
        const val coroutinesmanager = "com.rasalexman.coroutinesmanager:coroutinesmanager:${Versions.coroutinesManager}"

        //----- DI
        const val kodi = "com.rasalexman.kodi:kodi:${Versions.kodi}"

        //---- LOGGING TIMER =)
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "com.android.support.test:runner:${Versions.runner}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    }

}