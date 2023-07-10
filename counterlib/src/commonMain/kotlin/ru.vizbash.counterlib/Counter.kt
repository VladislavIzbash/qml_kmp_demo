@file:OptIn(ExperimentalJsExport::class)

package ru.vizbash.counterlib

@JsExport
data class CounterState(
    val count: Int = 0,
)

@JsExport
class Counter {
    private val _state = MutableObservable(CounterState())
    val state: Observable<CounterState> = _state

    fun increment() {
        _state.value = _state.value.copy(count = _state.value.count + 1)
    }
}