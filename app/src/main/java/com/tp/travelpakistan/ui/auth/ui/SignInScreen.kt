@file:OptIn(ExperimentalMaterial3Api::class)

package com.tp.travelpakistan.ui.auth.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.auth.data.models.SignInRequestBody
import com.tp.travelpakistan.ui.auth.data.models.SignUpRequestBody
import com.tp.travelpakistan.ui.auth.data.models.User
import com.tp.travelpakistan.ui.auth.ui.components.AuthTextField
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SignInScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    goToSignUp: () -> Unit = {},
    goToHome: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    var emailAddress by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val snackbarHostState = remember { SnackbarHostState() }


    val uiState = authViewModel.authUiState.collectAsState()


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {

        if (uiState.value.isSuccess) {
            goToHome()
            authViewModel.authenticated()
        }

        if (uiState.value.isError) {
            LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                // taking the `snackbarHostState` from the attached `scaffoldState`
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = "Authentication Problem, Check credentials",
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
            InputFieldErrorType.Password -> {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Password should not be empty!",
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
            else -> {

            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)

        ) {

            if (uiState.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .zIndex(3f))
            }
            Image(
                painter = painterResource(id = R.drawable.travel_pakistan_logo),
                contentDescription = "travel_logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(160.dp)
                    .clip(
                        CircleShape
                    )
                    .padding(top = 30.dp)
            )



            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))
                AuthTextField(
                    placeholder = { Text(text = "Username") },
                    value = emailAddress,
                    onValueChange = { emailAddress = it }
                )

                Spacer(modifier = Modifier.height(20.dp))
                AuthTextField(
                    placeholder = { Text(text = "Password") },
                    isPasswordField = true,
                    value = password,
                    onValueChange = { password = it }
                )

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                scope.launch {
                                    authViewModel.authenticateUser(
                                        SignInRequestBody(
                                            username = emailAddress.text,
                                            password = password.text
                                        )
                                    )
                                }
                            },
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .width(254.dp)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                        ) {
                            Text(text = "Sign In", style = TextStyle(fontSize = 18.sp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        ClickableText(
                            text = AnnotatedString("Forgot password?"),
                            onClick = { },
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Default,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.End
                            ),
                            modifier = Modifier.width(254.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(90.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .height(1.dp)
                                .width(120.dp)
                        )

                        Text(
                            text = "Or",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(
                            modifier = Modifier
                                .background(Color.DarkGray)
                                .height(1.dp)
                                .width(120.dp)
                        )

                    }
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sign in using",
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        IconButton(
                            onClick = { /**/ },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = "google"
                            )
                        }
                        IconButton(
                            onClick = { /**/ },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_fb),
                                contentDescription = "fb"
                            )
                        }
                        IconButton(
                            onClick = { /**/ },
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_twitter),
                                contentDescription = "twitter"
                            )
                        }
                    }
                }
            }
            ClickableText(
                text = AnnotatedString("Not have an account? Sign Up"),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(20.dp),
                onClick = { goToSignUp.invoke() },
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Default,
                    textDecoration = TextDecoration.None,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignInScreenPreview(
) {
    SignInScreen()
}