package com.rafi.pertemuan7.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rafi.pertemuan7.ui.view.screen.DetailView
import com.rafi.pertemuan7.ui.view.screen.MahasiswaFormView
import com.rafi.pertemuan7.ui.view.screen.RencanaStudyView
import com.rafi.pertemuan7.ui.view.screen.SplashView
import com.rafi.pertemuan7.ui.view.viewmodel.MahasiswaViewModel
import com.rafi.pertemuan7.ui.view.viewmodel.RencanaStudyViewModel

enum class Halaman{
    Splash,
    Mahasiswa,
    Matakuliah,
    Tampil
}

@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    krsViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val mahasiswaUiState = mahasiswaViewModel.dataModel.collectAsState().value
    val krsUiState = krsViewModel.krsStateUi.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = Modifier.padding()
    ){
        composable(route = Halaman.Splash.name){
            SplashView(
                onMulaiButton = {
                    navController.navigate(Halaman.Mahasiswa.name)
                }
            )
        }

        composable(route = Halaman.Mahasiswa.name){
            MahasiswaFormView(
                onSubmitButtonClick = {
                    mahasiswaViewModel.saveDataMhs(it)
                    navController.navigate(Halaman.Matakuliah.name)
                },
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Halaman.Matakuliah.name){
            RencanaStudyView(
                mahasiswa = mahasiswaUiState,
                onSubmitButtonClicked = {
                    krsViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.Tampil.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Halaman.Tampil.name){
            DetailView(
                dataMHS = mahasiswaUiState,
                dataKRS = krsUiState,
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}