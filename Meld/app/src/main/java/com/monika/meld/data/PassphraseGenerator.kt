package com.realllydan.meld.data

import android.content.Context

class PassphraseGenerator() {
    fun getPassphrase(context: Context): String? {

        val d = Dice()
        val wl = Wordlist()
        var bleh = ""
        for (i in 1..7)
            bleh += (wl.getWord(context, d.rand()).toString() + " ")
        return bleh
    }
}





