package com.psbn.firstblockpractice.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import com.psbn.firstblockpractice.core.R
import com.psbn.firstblockpractice.auth.R as authR

private const val EMPTY_STROKE = ""
private const val MIN_SYMBOLS = 6

@Composable
fun AuthContent(
    onBackIconClickListener: () -> Unit,
    onLoginButtonClickListener: () -> Unit
) {
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(
                rememberScrollState()
            ),
    ) {
        TopBar(onBackIconClickListener)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.spacing_large),
                end = dimensionResource(id = R.dimen.spacing_large)
            )
        ) {
            BigSpacer()
            AuthTopHintText()

            SmallSpacer()
            SocialAuth()

            BigSpacer()
            AuthBotHintText()

            SmallSpacer()
            val emailIsValid = rememberSaveable { mutableStateOf(false) }
            val passIsValid = rememberSaveable { mutableStateOf(false) }
            AuthEmailEditText(emailIsValid)
            SmallSpacer()
            AuthPassEditText(passIsValid)

            SmallSpacer()
            val authIsValid = rememberSaveable { mutableStateOf(false) }
            authIsValid.value = emailIsValid.value && passIsValid.value
            LoginButton(onLoginButtonClickListener, authIsValid)

            SmallSpacer()
        }
        FooterText()
        BigSpacer()
    }
}

@Composable
fun BigSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.big_spacing)))
}

@Composable
fun SmallSpacer() {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_spacing)))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onBackIconClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(
            colorResource(id = R.color.turtle_green)
        ),
        title = {
            Text(
                text = stringResource(id = R.string.label_auth),
                fontFamily = FontFamily(
                    Font(R.font.officina_sans_extra_bold, FontWeight.ExtraBold)
                )
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = authR.drawable.icon_back),
                contentDescription = stringResource(id = R.string.desc_back),
                modifier = Modifier
                    .clickable {
                        onBackIconClickListener()
                    }
                    .padding(start = dimensionResource(id = R.dimen.spacing_large))
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.turtle_green),
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Composable
fun AuthTopHintText() {
    Text(
        text = stringResource(id = R.string.text_social_auth),
        Modifier.width(dimensionResource(id = R.dimen.auth_text_width)),
        color = colorResource(id = R.color.black_70)
    )
}

@Composable
fun SocialAuth() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.width(dimensionResource(id = R.dimen.auth_text_width))
    ) {
        Image(
            painter = painterResource(id = authR.drawable.vk),
            contentDescription = stringResource(id = R.string.desc_auth_with_vk),
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = authR.drawable.fb),
            contentDescription = stringResource(id = R.string.desc_auth_with_fb),
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = authR.drawable.ok),
            contentDescription = stringResource(id = R.string.desc_auth_with_ok),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AuthBotHintText() {
    Text(
        text = stringResource(id = R.string.label_auth_with_app_text),
        Modifier.width(dimensionResource(id = R.dimen.auth_text_width)),
        color = colorResource(id = R.color.black_70)
    )
}

@Composable
fun AuthEmailEditText(emailIsValid: MutableState<Boolean>) {
    var text by rememberSaveable { mutableStateOf(EMPTY_STROKE) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            emailIsValid.value = text.length >= MIN_SYMBOLS
        },
        label = { Text(stringResource(id = R.string.e_mail)) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_email),
                color = colorResource(id = R.color.black_38)
            )
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            focusedLabelColor = colorResource(id = R.color.black_38),
            unfocusedLabelColor = colorResource(id = R.color.black_38),
            focusedIndicatorColor = colorResource(id = R.color.black_38),
            unfocusedIndicatorColor = colorResource(id = R.color.black_38),
            disabledIndicatorColor = colorResource(id = R.color.black_38),
        )
    )
}

@Composable
fun AuthPassEditText(passIsValid: MutableState<Boolean>) {
    var text by rememberSaveable { mutableStateOf(EMPTY_STROKE) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = {
            text = it
            passIsValid.value = text.length > MIN_SYMBOLS
        },
        label = { Text(stringResource(id = R.string.label_pass)) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_password),
                color = colorResource(id = R.color.black_38)
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = White,
            focusedContainerColor = White,
            focusedLabelColor = colorResource(id = R.color.black_38),
            unfocusedLabelColor = colorResource(id = R.color.black_38),
            focusedIndicatorColor = colorResource(id = R.color.black_38),
            unfocusedIndicatorColor = colorResource(id = R.color.black_38),
            disabledIndicatorColor = colorResource(id = R.color.black_38),
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisible) {
                stringResource(authR.string.desc_hide_password)
            } else {
                stringResource(authR.string.desc_show_password)
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = image,
                    description,
                    tint = colorResource(id = R.color.black_38)
                )
            }
        }
    )
}

@Composable
fun LoginButton(onBackIconClickListener: () -> Unit, authIsValid: MutableState<Boolean>) {
    Button(
        onClick = { onBackIconClickListener() },
        modifier = Modifier
            .fillMaxWidth(),
        enabled = authIsValid.value,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.turtle_green)),
        shape = RectangleShape
    ) {
        Text(
            text = stringResource(id = R.string.label_enter_btn),
            fontSize = textSizeResource(resId = R.dimen.font_medium),
            fontFamily = FontFamily(
                Font(R.font.roboto_medium)
            )
        )
    }
}

@Composable
fun FooterText() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.spacing_large),
            end = dimensionResource(id = R.dimen.spacing_large)
        )
    ) {
        Text(
            text = stringResource(id = R.string.label_forget_pass_auth),
            Modifier.weight(1f),
            color = colorResource(id = R.color.turtle_green),
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = stringResource(id = R.string.label_registration_auth),
            Modifier.weight(1f),
            textAlign = TextAlign.End,
            color = colorResource(id = R.color.turtle_green),
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun textSizeResource(resId: Int): TextUnit {
    return with(LocalDensity.current) {
        dimensionResource(resId).toSp()
    }
}
