package com.mohammedalaa.challenge001.ui

fun <K, V> getKeyForValue(map: Map<K, V>, targetValue: V): K? {
    for ((key, value) in map) {
        if (value == targetValue) {
            return key
        }
    }
    return null
}