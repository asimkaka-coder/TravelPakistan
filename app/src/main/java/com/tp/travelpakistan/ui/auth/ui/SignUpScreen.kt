@file:OptIn(ExperimentalMaterial3Api::class)

package com.tp.travelpakistan.ui.auth.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.auth.data.models.SignUpRequestBody
import com.tp.travelpakistan.ui.auth.data.models.User
import com.tp.travelpakistan.ui.auth.ui.components.AuthTextField
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    goToSignIn: () -> Unit = {},
    onRegisterSuccess: () -> Unit = {}
) {

    val authScope = rememberCoroutineScope()
    var username by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var emailAddress by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var cnic by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var address by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val snackbarHostState = remember { SnackbarHostState() }


    val uiState = authViewModel.authUiState.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {

        if (uiState.value.isSuccess) {
            onRegisterSuccess()
            authViewModel.authenticated()
        }

        if (uiState.value.isError) {
            LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                // taking the `snackbarHostState` from the attached `scaffoldState`
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = "Something Went Wrong, Try Again",
                    actionLabel = "Okay"
                )
                when (snackbarResult) {
                    SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                    SnackbarResult.ActionPerformed -> {
                        authViewModel.authenticated()
                    }
                }
            }
        }

        when (uiState.value.inputErrorType) {
            InputFieldErrorType.Address -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Address should not be empty!",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
            InputFieldErrorType.CNIC -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Enter Valid CNIC",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
            InputFieldErrorType.Email -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Enter valid email address",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
            InputFieldErrorType.NONE -> {

            }
            InputFieldErrorType.Password -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Password should consists of capital, small, numbers and special char",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
            InputFieldErrorType.PhoneNumber -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Enter valid phone number",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
            InputFieldErrorType.Username -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Username should not be empty!",
                        actionLabel = "Okay"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            authViewModel.authenticated()
                        }
                    }
                }
            }
        }



        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {

            if (uiState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(3f)
                )
            }
            ClickableText(
                text = AnnotatedString("Already have an account? Sign In"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = { goToSignIn.invoke() },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.None,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )


            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .wrapContentHeight()
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.travel_pakistan_logo),
                    contentDescription = "travel_logo",
                    modifier = Modifier
                        .size(160.dp)
                        .clip(
                            CircleShape
                        )
                        .padding(top = 10.dp)
                )
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))
                AuthTextField(
                    placeholder = { Text(text = "Username") },
                    value = username,
                    onValueChange = { username = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                AuthTextField(
                    placeholder = { Text(text = "Email Address") },
                    value = emailAddress,
                    onValueChange = { emailAddress = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                AuthTextField(
                    placeholder = { Text(text = "CNIC (with dashes)") },
                    value = cnic,
                    onValueChange = { cnic = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                AuthTextField(
                    placeholder = { Text(text = "Phone Number") },
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                AuthTextField(
                    placeholder = { Text(text = "Address") },
                    value = address,
                    onValueChange = { address = it }
                )

                Spacer(modifier = Modifier.height(10.dp))
                AuthTextField(
                    placeholder = { Text(text = "Password") },
                    value = password,
                    onValueChange = { password = it },
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            val user = SignUpRequestBody(
                                username = username.text,
                                email = emailAddress.text,
                                password = password.text,
                                address = address.text,
                                cnic = cnic.text,
                                phone = phoneNumber.text,
                                image = "testimage"
                            )
                            authScope.launch { authViewModel.registerUser(user) }
                        },
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .width(254.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                    ) {
                        Text(text = "Sign Up", style = TextStyle(fontSize = 18.sp))
                    }
                }
                Spacer(modifier = Modifier.height(90.dp))
            }

        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(
) {
//    SignUpScreen()
}