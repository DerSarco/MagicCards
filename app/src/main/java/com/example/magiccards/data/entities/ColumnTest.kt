package com.example.magiccards.data.entities

data class ColumnTest(
    val name: String,
    val description: String,
)

fun getColumnTestData(): List<ColumnTest> = (1..10).map {
    ColumnTest("Generic name $it", "generic description for future cards loadede in this app")
}

