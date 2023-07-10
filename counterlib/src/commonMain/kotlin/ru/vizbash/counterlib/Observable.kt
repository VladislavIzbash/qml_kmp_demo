@file:OptIn(ExperimentalJsExport::class)

package ru.vizbash.counterlib

typealias Observer<T> = (T) -> Unit

@JsExport
interface Observable<T> {
    val value: T

    fun observe(observer: Observer<T>)

    fun unsubscribe(observer: Observer<T>)

    fun unsubscribeAll()
}

@JsExport
class MutableObservable<T>(initialValue: T) : Observable<T> {

    override var value = initialValue
        set(value) {
            field = value

            for (observer in observers) {
                observer.invoke(value)
            }
        }

    private val observers = mutableListOf<Observer<T>>()

    override fun observe(observer: Observer<T>) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer<T>) {
        observers.remove(observer)
    }

    override fun unsubscribeAll() {
        observers.clear()
    }
}