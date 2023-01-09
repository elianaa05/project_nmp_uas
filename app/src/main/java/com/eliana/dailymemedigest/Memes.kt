package com.eliana.dailymemedigest

data class Memes(
    var id:Int,
    var url:String,
    var text_atas:String,
    var text_bawah:String,
    var tanggal:String,
    var id_pembuat:Int,
    var jumlah_like:Int
)
