package com.example.lokalko.data.preferences

import android.content.SharedPreferences
import com.example.lokalko.data.model.ReportData
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_ADDRESS
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_CATEGORY_ID
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_CITY_ID
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_DESCRIPTION
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_SEVERITY_ID
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_REPORT_TITLE
import com.example.lokalko.data.preferences.Preferences.Companion.KEY_SHOW_DIALOG
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): Preferences {
    override fun saveToken(token: String) {
        println("TOKEN")
        println(token)
        sharedPreferences.edit().putString(Preferences.KEY_TOKEN, token).apply()
    }

    override fun readToken(): String? {
        return sharedPreferences.getString(Preferences.KEY_TOKEN, null)
    }

    override fun deleteToken() {
        sharedPreferences.edit().remove(Preferences.KEY_TOKEN).apply()
    }

    override fun saveLatitude(latitude: Double) {
        println("PREFS LAT")
        println(latitude)
        sharedPreferences.edit().putString(Preferences.KEY_LATITUDE, latitude.toString()).apply()
    }

    override fun readLatitude(): Double {
        val latitudeString = sharedPreferences.getString(Preferences.KEY_LATITUDE, null)
        return latitudeString?.toDoubleOrNull() ?: 0.0
    }

    override fun saveLongitude(longitude: Double) {
        println("PREFS LONG")
        println(longitude)
        sharedPreferences.edit().putString(Preferences.KEY_LONGITUDE, longitude.toString()).apply()
    }

    override fun readLongitude(): Double {
        val longitudeString = sharedPreferences.getString(Preferences.KEY_LONGITUDE, null)
        return longitudeString?.toDoubleOrNull() ?: 0.0
    }

    override fun saveShowDialog(showDialog: Boolean) {
        sharedPreferences.edit().putBoolean(KEY_SHOW_DIALOG, showDialog).apply()
    }

    override fun readShowDialog(): Boolean {
        return sharedPreferences.getBoolean(KEY_SHOW_DIALOG, false)
    }

    override fun saveReportData(
        title: String,
        description: String,
        address: String,
        cityId: Int,
        categoryId: Int,
        severityId: Int
    ) {
        sharedPreferences.edit().apply {
            putString(KEY_REPORT_TITLE, title)
            putString(KEY_REPORT_DESCRIPTION, description)
            putString(KEY_REPORT_ADDRESS, address)
            putInt(KEY_REPORT_CITY_ID, cityId)
            putInt(KEY_REPORT_CATEGORY_ID, categoryId)
            putInt(KEY_REPORT_SEVERITY_ID, severityId)
            apply()
        }
    }

    override fun readReportData(): ReportData {
        val title = sharedPreferences.getString(KEY_REPORT_TITLE, "") ?: ""
        val description = sharedPreferences.getString(KEY_REPORT_DESCRIPTION, "") ?: ""
        val address = sharedPreferences.getString(KEY_REPORT_ADDRESS, "") ?: ""
        val cityId = sharedPreferences.getInt(KEY_REPORT_CITY_ID, 0)
        val categoryId = sharedPreferences.getInt(KEY_REPORT_CATEGORY_ID, 0)
        val severityId = sharedPreferences.getInt(KEY_REPORT_SEVERITY_ID, 0)

        return ReportData(title, description, address, cityId, categoryId, severityId)
    }
}