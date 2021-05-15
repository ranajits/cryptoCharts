package com.ranajit.tredzerv.base

interface UICallbacks<V> {
    fun getViewModel(): Class<V>
    fun getLayoutId(): Int
    fun onBindingUi()
}