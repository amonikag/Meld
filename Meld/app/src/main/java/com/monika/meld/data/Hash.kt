package com.realllydan.meld.data

import java.math.BigInteger
import java.security.MessageDigest

class Hash {
    fun getHashFromText(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}