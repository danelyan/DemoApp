package ru.cometrica.demoapp

import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class BusinessLogicTest {

    @Test
    fun plus() {
        assertEquals(BusinessLogic().plus(1, 1), 2)
    }

    @Test
    fun test() {
        getValue()
            .subscribe({ println("$it\n") }, { System.err.println(it) })
    }

    fun getValueFromCache(): Maybe<String> = Maybe.empty()

    fun getValueFromCloud() = Maybe.empty<String>()

    fun getValue(): Single<String> =
        getValueFromCache()
            .switchIfEmpty(getValueFromCloud())
            .toSingle("")
}
