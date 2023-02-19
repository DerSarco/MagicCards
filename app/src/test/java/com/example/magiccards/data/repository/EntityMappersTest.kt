package com.example.magiccards.data.repository

import com.example.magiccards.data.entities.MagicCardEntity
import com.example.magiccards.util.getFakeCardListService
import com.example.magiccards.util.getFakeCardService
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveTheSameClassAs
import org.junit.Test

class EntityMappersTest {

    @Test
    fun `mapper SHOULD return a list of entity cards WHEN API list is provided`() {
        val mapped = getFakeCardListService().toCard()
        val first = mapped.first()

        first::class.java shouldHaveTheSameClassAs MagicCardEntity::class.java
        mapped.first().id shouldBeEqualTo getFakeCardListService().cards.first().id
    }

    @Test
    fun `mapper SHOULD return a card of entity card WHEN API card is provided`() {
        val mapped = getFakeCardService().toSingleCardEntity()
        mapped::class.java shouldHaveTheSameClassAs MagicCardEntity::class.java
        mapped.id shouldBeEqualTo getFakeCardService().id
    }

}