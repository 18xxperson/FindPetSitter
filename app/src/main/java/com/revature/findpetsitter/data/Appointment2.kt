package com.revature.findpetsitter.data

data class Appointment2 (
    val id:Int,
    val user_id:Int,
    val sitter_id:Int,
    var start_date:String,
    var end_date:String,
    val service_type:String,
    var total_price:Float
)