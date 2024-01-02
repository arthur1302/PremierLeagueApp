package com.example.premierleagueapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.premierleagueapp.R

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    Column(Modifier.padding(start = 8.dp, end = 8.dp).testTag(stringResource(R.string.contact_content))) {
        Text(text = "If you have any questions or would like to get in touch, you can reach me via the email address below:", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(25.dp))
        ClickableText(text = buildAnnotatedString { append("arthur.haus@student.hogent.be") }, onClick = { onClick(context) }, style = MaterialTheme.typography.bodyLarge)
    }
}

fun onClick(context: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:arthur.haus@student.hogent.be")
    intent.putExtra(Intent.EXTRA_EMAIL, "test")
    intent.putExtra(Intent.EXTRA_SUBJECT, "Hello World")
    if (intent.resolveActivity(context.packageManager) != null) {
        startActivity(context, intent, null)
    } else {
        // Handle the case where no activity is available
        Toast.makeText(context, "No email client found", Toast.LENGTH_SHORT).show()
    }
}
