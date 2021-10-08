package com.example.myapplication.models

data class Ingredients(val malt: Array<Malt>, val hops: Array<Hops>, val yeast: String)

// "ingredients":{"malt":[{"name":"Extra Pale","amount":{"value":12.5,"unit":"kilograms"}}],"hops":[{"name":"Nelson Sauvin","amount":{"value":6.25,"unit":"grams"},"add":"start","attribute":"bitter"},{"name":"Centennial","amount":{"value":12.5,"unit":"grams"},"add":"start","attribute":"bitter"},{"name":"Nelson Sauvin","amount":{"value":12.5,"unit":"grams"},"add":"end","attribute":"flavour"},{"name":"Amarillo","amount":{"value":12.5,"unit":"grams"},"add":"end","attribute":"flavour"},{"name":"Centennial","amount":{"value":12.5,"unit":"grams"},"add":"end","attribute":"flavour"}],"yeast":"Wyeast 3522 - Belgian Ardennes™"},

