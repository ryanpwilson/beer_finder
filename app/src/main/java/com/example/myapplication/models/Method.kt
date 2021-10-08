package com.example.myapplication.models

data class Method(val mash_temp: Array<Temp>, val fermentation: Temp, val twist: String)

// "method":{"mash_temp":[{"temp":{"value":65,"unit":"celsius"},"duration":75}],"fermentation":{"temp":{"value":20,"unit":"celsius"}},"twist":"Nettles: 25g at end, Juniper: 25g at end"},
