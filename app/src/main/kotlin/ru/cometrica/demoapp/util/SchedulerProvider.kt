package ru.cometrica.demoapp.util

import io.reactivex.Scheduler

/**
 * SchedulerProvider.
 */

interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler

    fun db(): Scheduler

    fun sync(): Scheduler

}