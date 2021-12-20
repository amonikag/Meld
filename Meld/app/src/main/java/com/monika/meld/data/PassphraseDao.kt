package com.realllydan.meld.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PassphraseDao {
    @Query("SELECT * FROM passphrases")
    fun getAll(): List<Passphrase>

    @Insert
    fun insert(passphrase: Passphrase)

    @Delete
    fun delete(passphrase: Passphrase)
}