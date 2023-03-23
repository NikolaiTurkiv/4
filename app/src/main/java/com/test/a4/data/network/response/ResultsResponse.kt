package com.test.a4.data.network.response

import com.test.a4.ui.adapters.Delegates

data class ResultsResponse(
    val date: String,
    val match: String,
    val count: String,
    val competition: String,
    val result: String,
    val image: String,
): Delegates