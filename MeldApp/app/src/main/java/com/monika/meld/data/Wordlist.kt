package com.realllydan.meld.data

import android.content.Context

class Wordlist() {
    private fun import(context: Context): Map<Int, String> {
        val wordList = mutableMapOf<Int, String>()
        val inStream = context.assets.open("wordlist.txt")
        inStream.bufferedReader().forEachLine {
            wordList[it.substring(0, 5).toInt()] = it.substring(6, it.length)
        }
        return wordList
    }

    fun getWord(context: Context, key: Int): String? {
        val wl = import(context)
        return wl[key]
    }
}