package me.aartikov.sesamecomposesample.counter

interface CounterComponent {

    val count: Int

    val minusButtonEnabled: Boolean

    val plusButtonEnabled: Boolean

    fun onMinusButtonClick()

    fun onPlusButtonClick()
}