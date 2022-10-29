package com.antigua.mynoteroom.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antigua.mynoteroom.ui.home.HomeScreen
import com.antigua.mynoteroom.ui.home.NoteScreen
import com.antigua.mynoteroom.ui.theme.MyNoteRoomTheme
import com.antigua.mynoteroom.viewmodel.HomeViewModelAbstract

//enum class Screen {
//    Home, Note
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteRootApp (
    homeViewModel: HomeViewModelAbstract,
){
    val navController = rememberNavController()
    MyNoteRoomTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            HomeScreen(
//                homeViewModel = homeViewModel,
//               onClickNote = navController.navigate(Screen.Note.name)
//            )
            NavHost(
                navController = navController,
                startDestination = Screen.Home.name,
                builder = {
                    composable(Screen.Home.name) {
                        HomeScreen(
                            homeViewModel = homeViewModel,
                            onClickNote = {
                                navController.navigate(Screen.Note.name)
                            },
//                            onClickAddNote = {
//                                navController.navigate(Screen.Note.name)
//                            }
                        )
                    }
                    composable(Screen.Note.name) {
                        NoteScreen(
                            viewModel = homeViewModel,
//                            onClickClose = {
//                                navController.popBackStack()
//                            },
                        )
                    }
                })
        }
    }

}