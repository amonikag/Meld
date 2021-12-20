package com.realllydan.meld.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Passphrase::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passphraseDao(): PassphraseDao
}