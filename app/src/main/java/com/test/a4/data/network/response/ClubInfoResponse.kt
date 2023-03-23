package com.test.a4.data.network.response

import com.test.a4.ui.adapters.Delegates

data class ClubInfoResponse(
    val name: String,
    val full_name: String,
    val nicknames: String,
    val founded: String,
    val website: String,
    val venue_name: String,
    val capacity: String,
    val manager: String,
    val chairman: String,
    val image: String,
): Delegates
