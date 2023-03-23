package com.test.a4.data.network.response

import com.test.a4.ui.adapters.Delegates

data class SquadResponse(
    val image: String,
    val name: String,
    val position: String,
    val age: String,
    val ht: String,
    val wt: String,
    val nat: String,
): Delegates