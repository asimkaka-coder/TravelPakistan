package com.tp.travelpakistan.ui.auth.data

import android.content.Context
import com.tp.travelpakistan.PreferenceManager
import com.tp.travelpakistan.ui.auth.data.models.UserX
import com.tp.travelpakistan.ui.auth.data.models.UserXX
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferenceManager: PreferenceManager
) {

    var userInfo: UserX? = null


    fun initUserSession(userX: UserX) {
        userInfo = userX
        preferenceManager.setUserLoggedIn(true)
        preferenceManager.setCurrentUser(userX)
    }

    fun initUserSession(userX: UserXX) {
        userInfo = userX.toUserX()
        preferenceManager.setUserLoggedIn(true)
        preferenceManager.setCurrentUser(userX.toUserX())
    }

    fun endUserSession() = preferenceManager.setLogout()

}