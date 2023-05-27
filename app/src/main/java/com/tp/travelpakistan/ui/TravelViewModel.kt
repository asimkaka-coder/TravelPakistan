package com.tp.travelpakistan.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.home.ui.components.BottomNavigationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {


    fun logoutUser() = userSession.endUserSession()

    var bottomNavItems = mutableStateOf(
         listOf<BottomNavigationItem>(
            BottomNavigationItem(
                itemName = "Home",
                icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "home") },
                isSelected = true
            ),
            BottomNavigationItem(
                itemName = "Explore",
                icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "explore") }
            ),
            BottomNavigationItem(
                itemName = "Account",
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "account"
                    )
                }
            )
        ))

    val selectedBottomItem = mutableStateOf(
        bottomNavItems.value.first()
    )

    fun onBottomNavItemClick(selectedItem: BottomNavigationItem){
        val oldList = bottomNavItems.value
        val newList = oldList.map NavMap@{navItem->
            if(navItem.itemName == selectedItem.itemName){
                return@NavMap navItem.copy(isSelected = true)
            }else{
                return@NavMap  navItem.copy(isSelected = false)
            }
        }
        bottomNavItems.value = newList
        selectedBottomItem.value = selectedItem
    }

}