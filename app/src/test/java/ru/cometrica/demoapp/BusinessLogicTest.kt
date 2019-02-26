package ru.cometrica.demoapp

import org.junit.Assert.assertEquals
import org.junit.Test

class BusinessLogicTest {

    @Test
    fun plus() {
        assertEquals(BusinessLogic().plus(1, 1), 2)
    }
}