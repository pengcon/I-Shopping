package com.example.ishopping.ui.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun EditText.textChanges(): Flow<String> = callbackFlow {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            trySend(s.toString())
        }
        override fun afterTextChanged(s: Editable?) {}
    }
    addTextChangedListener(textWatcher)
    awaitClose { removeTextChangedListener(textWatcher) }
}

