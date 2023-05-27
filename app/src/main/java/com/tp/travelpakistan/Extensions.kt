package com.tp.travelpakistan

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.core.net.toUri
import com.tp.travelpakistan.ui.dialog_rate.RateUsDialog
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max

fun Modifier.noRippleClickable(onClick: () -> Unit) = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

tailrec fun Context.getActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.rateUsDialog(action: () -> Unit) = RateUsDialog(
    context = this,
    onDismissDialog = action,
    onRateUsDialog = {
        if (it >= 5) {
            rateOnPlayStore()
        } else {
            toast("Thank you for your feedback")
        }
        action()
    }
).show()

fun Context.openLink(link: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, link.toUri()))
    } catch (e: Exception) {
        toast("Failed to find suitable application for opening link")
    }
}

fun Context.restartApplication() = this.apply {
    startActivity(Intent(this, javaClass).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    })
}

fun Context.createEmail(email: String, subject: String, body: String = "") {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }

    startActivity(intent)
}

fun Context.sendMail(link: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(link))
            putExtra(Intent.EXTRA_SUBJECT, "Photo Studio feedback")
        }
        startActivity(intent)
    } catch (e: Exception) {
        toast("Failed to find suitable application for opening link")
    }
}

fun Context.toast(message: String) =
    Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()

fun Context.rateOnPlayStore() {
    val uri = Uri.parse("market://details?id=" + this.packageName)
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        toast("Couldn't launch the market")
    }
}

val Number.dpToPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )

@Composable
fun showSnackbarErrorMessage(
    message: String,
    snackbarHostState: SnackbarHostState,
    actionLabel: String = "Okay",
    action: () -> Unit
) {
    LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
        // taking the `snackbarHostState` from the attached `scaffoldState`
        val snackbarResult = snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel
        )
        when (snackbarResult) {
            SnackbarResult.Dismissed -> {
                Log.d("SnackbarDemo", "Dismissed")
                action
            }
            SnackbarResult.ActionPerformed -> {
                action()
            }
        }
    }
}

fun Long.formatDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val selectedDate = Date(this)
    return dateFormat.format(selectedDate)
}