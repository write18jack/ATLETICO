package com.example.atletico.ui.schedule

data class Table(
    val records: List<Record>
)

data class Record(
    val draw: Int,
    val goalsAgainst: Int,
    val goalsFor: Int,
    val loss: Int,
    val played: Int,
    val points: Int,
    val team: String,
    val win: Int
)