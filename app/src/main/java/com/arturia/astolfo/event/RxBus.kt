package com.arturia.astolfo.event

import android.util.Log
import rx.Observable
import rx.Subscriber
import rx.subjects.PublishSubject

/**
 * Author: Arturia
 * Date: 2017/12/4
 */
class RxBus {

    private object Holder {
        val instance = RxBus()
    }

    companion object {

        fun get(): RxBus = Holder.instance

        fun defaultSubscriber(): Subscriber<Any> =
            object : Subscriber<Any>() {
                override fun onError(e: Throwable?) {
                    Log.e("RxBus", "RxBus onError: " + e?.message)
                }

                override fun onNext(t: Any?) {
                    Log.d("RxBus", "RxBus onNext")
                }

                override fun onCompleted() {
                    Log.d("RxBus", "RxBus onCompleted")
                }
            }
    }

    private val eventBus: PublishSubject<Any> = PublishSubject.create()

    fun post(event: Any) {
        eventBus.onNext(event)
    }

    fun toObservable(): Observable<Any> = eventBus
}