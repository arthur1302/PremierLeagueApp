package com.example.premierleagueapp.ui.components.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.core.content.ContextCompat
import com.example.premierleagueapp.R

@Composable
fun MailComponent() {
    val context = LocalContext.current
    ClickableText(text = buildAnnotatedString { append(stringResource(R.string.mail_address)) }, onClick = { onClick(context) }, style = MaterialTheme.typography.bodyLarge)
}

@SuppressLint("QueryPermissionsNeeded")
fun onClick(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:arthur.haus@student.hogent.be")
    intent.putExtra(Intent.EXTRA_EMAIL, "Mail")
    intent.putExtra(Intent.EXTRA_SUBJECT, "Contact")
    if (intent.resolveActivity(context.packageManager) != null) {
        ContextCompat.startActivity(context, intent, null)
    } else {
        Toast.makeText(context, "No email client found", Toast.LENGTH_SHORT).show()
    }
}
