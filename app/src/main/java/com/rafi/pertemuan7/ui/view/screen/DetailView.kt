package com.rafi.pertemuan7.ui.view.screen

import androidx.compose.runtime.Composable
import com.rafi.pertemuan7.model.Mahasiswa
import com.rafi.pertemuan7.model.RencanaStudi

@Composable
fun DetailView(
    dataMHS: Mahasiswa,
    dataKRS: RencanaStudi,
    onBackButtonClicked: () -> Unit
){
    val listDataMHS = listOf(
        Pair("NIM", dataMHS.nim),
        Pair("Nama", dataMHS.nama),
        Pair("Email", dataMHS.email)
    )
    val listDataKRS = listOf(
        Pair("Mata Kuliah", dataKRS.mataKuliah),
        Pair("Kelas", dataKRS.kelas)
    )
}