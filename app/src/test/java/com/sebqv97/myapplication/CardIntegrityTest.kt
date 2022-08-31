package com.sebqv97.myapplication

import com.sebqv97.myapplication.core.util.CardValidation
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CardIntegrityTest {
    private  var card:CardValidation? = null


    @After
    fun teardown(){
        card = null
    }

    @Test
    fun `make a null card EXPECT null`(){
        card = CardValidation(null,null)
        assertNull(card!!.checkCardIntegrity())
    }

    @Test
    fun `make a card with card content expiry date null EXPECT NULL`(){
        card = CardValidation("card",null)
        assertEquals(card!!.checkCardIntegrity(),null)
    }

    @Test
    fun `make a card that contain letters EXPECT null`(){
        card = CardValidation("0223451234asd2","10/22")
        assertEquals(card!!.checkCardIntegrity(),null)
    }

    @Test
    fun `make a correct card with incorrect Length EXpect null`(){
        card = CardValidation(
            "1121-2322-223-0123","01/23")
        assertNull(card!!.checkCardIntegrity())
    }

    @Test
    fun `make a correct card with correct Expiry Date EXPECTING pass`() {
        card = CardValidation(
            "1121-2322-2243-0123", "01/23"
        )
        assertEquals("Acme",card!!.checkCardIntegrity())
    }
}