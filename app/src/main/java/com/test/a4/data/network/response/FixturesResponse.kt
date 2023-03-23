package com.test.a4.data.network.response

import com.test.a4.ui.adapters.Delegates

data class FixturesResponse(
    val date: String,
    val match: String,
    val time: String,
    val competition: String,
    val image: String,
): Delegates