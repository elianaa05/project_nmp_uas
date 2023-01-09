package com.eliana.dailymemedigest


data class Users(
    var id: Int,
    var username: String,
    var nama_depan: String,
    var nama_belakang: String,
    var password: String,
    var tanggal:String,
    var link:String,
    var pengaturan_privasi: Int
)
