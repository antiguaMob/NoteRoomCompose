package com.antigua.mynoteroom.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.antigua.mynoteroom.ui.home.HomeScreen
import com.antigua.mynoteroom.ui.theme.MyNoteRoomTheme
import com.antigua.mynoteroom.viewmodel.HomeViewModelAbstract

enum class Screen {
    Home, Note
}

@Composable
fun NoteRootApp (
    homeViewModel: HomeViewModelAbstract,
){
    MyNoteRoomTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                homeViewModel = homeViewModel
            )
        }
    }
 //   {
//  //      val navController = rememberNavController()
//        MyNoteRoomTheme{
//            // A surface container using the 'background' color from the theme
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colorScheme.background
//            ) {
//                NavHost(
//                    navController = navController,
//                    startDestination = Screen.Home.name,
//                    builder = {
//                        composable(Screen.Home.name) {
//                            HomeScreen(
//                                homeViewModel = homeViewModel,
//                                onClickNote = {
//                                    navController.navigate(Screen.Note.name)
//                                },
//                                onClickAddNote = {
//                                    navController.navigate(Screen.Note.name)
//                                }
//                            )
//                        }
//                        composable(Screen.Note.name) {
//                            NoteScreen(
//                                viewModel = homeViewModel,
//                                onClickClose = {
//                                    navController.popBackStack()
//                                },
//                            )
//                        }
//                    })
//            }
//        }
//    }
}