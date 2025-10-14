package com.fermer.home.presentation


import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fermer.home.presentation.today.TodayScreen
import com.fermer.home.presentation.upcoming.UpcomingScreen
import com.fermer.task.presentation.TaskListRoute

/*
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(HomeTab.Today) }

    Scaffold() { paddingValues ->
        Column( modifier = Modifier
            .padding(paddingValues)) {

            TabRow(
                selectedTabIndex = selectedTab.ordinal
            ) {
                HomeTab.entries.forEachIndexed { index, tab ->
                    Tab(
                        text = { Text(tab.title) },
                        selected = selectedTab.ordinal == index,
                        onClick = { selectedTab = tab }
                    )
                }
            }

            when (selectedTab) {
                HomeTab.Today -> TaskListRoute(isTodayTab = true)
                HomeTab.Upcoming -> TaskListRoute(isTodayTab = false)
            }
        }
    }

}

enum class HomeTab(val title: String) {
    Today("Today"),
    Upcoming("Upcoming")
}*/
@Composable
fun HomeScreen() {
    val tabs = listOf("Today", "Upcoming")
    var selectedTab by remember { mutableStateOf(0) }
    Scaffold() { paddingValues ->
        Column( modifier = Modifier
            .padding(paddingValues)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }

            when (selectedTab) {
                0 -> TodayScreen()
                1 -> UpcomingScreen()
            }
        }


    }
}