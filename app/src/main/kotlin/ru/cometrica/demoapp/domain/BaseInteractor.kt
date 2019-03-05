package ru.cometrica.demoapp.domain

interface Interactor<T> {
    fun build(): T
}

interface InteractorParametrized<P, T> {
    fun build(param: P): T
}
