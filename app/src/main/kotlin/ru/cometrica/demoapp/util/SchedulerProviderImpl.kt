package ru.cometrica.demoapp.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * SchedulerProviderImpl.
 */
class SchedulerProviderImpl : SchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun db(): Scheduler = Schedulers.io()

    override fun sync(): Scheduler = Schedulers.io()

}