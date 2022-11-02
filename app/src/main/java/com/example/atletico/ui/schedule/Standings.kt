package com.example.atletico.ui.schedule

data class Standings(
    val data: Data,
    val success: Boolean
)

data class Data(
    val table: List<Table>
)

data class Table(
    val competitionId: String?,
    val drawn: String,
    val goal_diff: String,
    val goals_conceded: String,
    val goals_scored: String,
    val group_id: String,
    val group_name: String,
    val league_id: String,
    val lost: String,
    val matches: String,
    val name: String,
    val points: String,
    val rank: String,
    val season_id: String,
    val stage_id: String,
    val stage_name: String,
    val team_id: String,
    val won: String
)