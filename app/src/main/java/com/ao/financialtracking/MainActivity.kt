package com.ao.financialtracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ao.financialtracking.dashboard.DashboardScreen
import com.ao.financialtracking.dashboard.navigation.DashboardRoute
import com.ao.financialtracking.ui.theme.FinancialTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinancialTrackingTheme {
                val navController = rememberNavController()
                
                NavHost(
                    navController = navController,
                    startDestination = DashboardRoute
                ) {
                    composable<DashboardRoute> {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}
