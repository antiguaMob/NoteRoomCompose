package com.antigua.mynoteroom.ui
// When using the Room persistence library to keep your user’s data locally, it is necessary to verify the reliability of the database and make sure the user’s data remains stable.
// With the testing of Room, you can validate that the database behavior remains reliable when running different queries on your data.
// In this video, we will add tests for the NoteRoomDao class which we created in an earlier video (Simple Note App using Room, Kotlin Flows, Hilt, and Jetpack Compose for Android)
//
// Github Repository with both Starting code and the completed state:
// https://github.com/superus8r/android-noteroom
// (Check out the relevant branches for each state)

// Part 1 - Create a simple Note App for Android using Room, Kotlin Flows, Hilt, and Jetpack Compose
// https://www.youtube.com/watch?v=rz8GuB_KgP8&list=PLc2U33zPL5QL1_ikO70XQQUgByhPYDOzt&index=1

// Part 2 - Create a Simple Note App: Test Room Database for Android
// https://www.youtube.com/watch?v=x-IQcIg96-8&list=PLc2U33zPL5QL1_ikO70XQQUgByhPYDOzt&index=2

// Part 3 - Create a Simple Note App: Improved UI - Jetpack Compose
// https://www.youtube.com/watch?v=9qFiOxG4kpk&list=PLc2U33zPL5QL1_ikO70XQQUgByhPYDOzt&index=3

// Part 4 - UI test   ?

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.antigua.mynoteroom.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

enum class Screen{
    Home, Note
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            NoteRootApp(homeViewModel = homeViewModel)
        }
    }
}
