package com.example.atletico.ui.schedule

data class Atletico(
    val address: String,
    val area: Area1,
    val clubColors: String,
    val coach: Coach,
    val crest: String,
    val founded: Int,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val runningCompetitions: List<RunningCompetition>,
    val shortName: String,
    val squad: List<Squad>,
    val staff: List<Any>,
    val tla: String,
    val venue: String,
    val website: String
)

data class Area1(
    val code: String,
    val flag: String,
    val id: Int,
    val name: String
)

data class Coach(
    val contract: Contract,
    val dateOfBirth: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val name: String,
    val nationality: String
)

data class RunningCompetition(
    val code: String,
    val emblem: String,
    val id: Int,
    val name: String,
    val type: String
)

data class Squad(
    val dateOfBirth: String,
    val id: Int,
    val name: String,
    val nationality: String,
    val position: String
)

data class Contract(
    val start: Any,
    val until: Any
)