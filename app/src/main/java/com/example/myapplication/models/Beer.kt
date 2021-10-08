package com.example.myapplication.models

data class Beer(
    val id : Int,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String,
    val image_url: String,
    val abv: Double,
    val ibu: Double,
    val target_fg: Int,
    val target_og: Double,
    val ebc:Double,
    val srm: Double,
    val ph: Double,
    val attenuation_level: Double,
    val volume: Volume,
    val boil_volume: Volume,
    val method: Method,
    val ingredients: Ingredients,
    val food_pairing: Array<String>,
    val brewers_tips: String,
    val contributed_by: String
)
