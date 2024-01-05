package com.example.premierleagueapp

import androidx.navigation.NavController
import junit.framework.TestCase.assertEquals

/**
 * Assertion function to check whether the expected route name is equal to the actual route name
 *
 * @author Arthur Haus
 *
 * @param expectedRouteName [String]
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}
