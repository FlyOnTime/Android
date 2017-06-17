package de.example.jw.mvi_app.scheduler

import io.reactivex.Scheduler

/**
 * Created by JW on 29.04.2017.
 */

interface SchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun trampoline(): Scheduler
    fun newThread(): Scheduler
    fun io(): Scheduler
}
