package com.meli.core.common.widgets

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun Context.showDialogError(onCancelClick: () -> Unit, onTryAgain: () -> Unit) {
    MaterialAlertDialogBuilder(this)
        .setTitle("Ops, algo deu errado")
        .setMessage("Tentamos carregar as informações mas não conseguimos")
        .setNegativeButton("Cancelar") { dialog, which ->
            onCancelClick()
        }
        .setPositiveButton("Tentar Novamente") { dialog, which ->
            onTryAgain.invoke()
        }
        .setCancelable(false)
        .show()
}