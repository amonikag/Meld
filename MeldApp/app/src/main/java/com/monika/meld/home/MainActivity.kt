package com.realllydan.meld.home

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.realllydan.meld.MeldTheme
import com.realllydan.meld.composables.MeldCard
import com.realllydan.meld.data.*

private const val TAG = "MainActivity"

lateinit var passphraseDao: PassphraseDao

class MainActivity : AppCompatActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "passphrases"
        ).allowMainThreadQueries().build()

        passphraseDao = db.passphraseDao()


        setContent {
            var passphraseList: List<Passphrase> by remember { mutableStateOf(
                passphraseDao.getAll()
            )}

            MeldTheme {
                BottomSheetScaffold(
                    sheetBackgroundColor = MaterialTheme.colors.primary,
                    sheetContent = {
                        Column(
                            Modifier
                                .fillMaxSize()
                        ) {
                            Icon(
                                Icons.Filled.ExpandLess,
                                "Expand bottom sheet",
                                tint = Color.White,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .size(48.dp)
                            )

                            Text(
                                text = "Previously",
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 40.sp,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    top = 32.dp,
                                    end = 16.dp,
                                    bottom = 8.dp
                                )
                            )

                            Spacer(modifier = Modifier.padding(top = 16.dp))

                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(all = 16.dp)
                            ) {
                                items(
                                    items = passphraseList
                                ) { Passphrase ->
                                    MeldCard(
                                        text = Passphrase.passphrase,
                                        color = Color(0xFF5F6466)
                                    ) {
                                        copyTextToClipboard(Passphrase.passphrase)
                                    }
                                }
                            }
                        }
                    }, sheetPeekHeight = 55.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(color = MaterialTheme.colors.primary)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        var passphrase: String by remember {
                            mutableStateOf(
                                PassphraseGenerator().getPassphrase(this@MainActivity).toString()
                            )
                        }

                        MeldCard (
                            text = passphrase,
                            color = Color(0xFF5F6466),
                            onClick = {
                                copyTextToClipboard(passphrase)
                            }
                        )

                        Spacer(modifier = Modifier.padding(top = 16.dp))

                        MeldCard (
                            text = Hash().getHashFromText(passphrase),
                            onClick = {
                                copyTextToClipboard(passphrase)
                            }
                        )

                        Spacer(modifier = Modifier.padding(top = 16.dp))

                        Row(
                            modifier = Modifier.align(Alignment.End)
                        ){
                            FloatingActionButton(
                                onClick = {
                                    persistPassphraseToRoom(passphrase)
                                    passphraseList = passphraseDao.getAll()
                                },
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .size(56.dp),
                                backgroundColor = Color(0xFF11C21F)
                            ) {
                                Icon(
                                    Icons.Filled.Save,
                                    "Save passphrase to device",
                                    tint = Color.White,
                                    modifier = Modifier.size(32.dp)
                                )
                            }

                            Spacer(modifier = Modifier.padding(top = 16.dp))

                            FloatingActionButton(
                                onClick = {
                                    passphrase =
                                        PassphraseGenerator().getPassphrase(this@MainActivity).toString()
                                },
                                modifier = Modifier
                                    .size(56.dp),
                                backgroundColor = Color(0xFF09A2F8)
                            ) {
                                Icon(
                                    Icons.Filled.Cached,
                                    "Generate New Passphrase",
                                    tint = Color.White,
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun persistPassphraseToRoom(passphraseToSave: String) {
        val passphrase = Passphrase(passphrase = passphraseToSave)
        passphraseDao.insert(passphrase)
    }

    private fun copyTextToClipboard(textToCopy: String) {
        val clipboard =
            getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clip = ClipData.newPlainText("Passphrase", textToCopy)
        clipboard.setPrimaryClip(clip)
    }

}