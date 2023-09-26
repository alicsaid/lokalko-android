package com.example.lokalko.data.preferences

import com.example.lokalko.data.model.ReportData

interface Preferences {
    fun saveToken(token: String)
    fun readToken(): String?
    fun deleteToken()
    fun saveLatitude(latitude: Double)
    fun readLatitude(): Double
    fun saveLongitude(longitude: Double)
    fun readLongitude(): Double
    fun saveReportData(title: String, description: String, address: String, cityId: Int, categoryId: Int, severityId: Int)
    fun readReportData(): ReportData
    fun saveShowDialog(showDialog: Boolean)
    fun readShowDialog(): Boolean
    companion object {
        const val KEY_TOKEN = "access_token"
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"
        const val KEY_REPORT_TITLE = "title"
        const val KEY_REPORT_DESCRIPTION = "description"
        const val KEY_REPORT_ADDRESS = "address"
        const val KEY_REPORT_CITY_ID = "cityId"
        const val KEY_REPORT_CATEGORY_ID = "categoryId"
        const val KEY_REPORT_SEVERITY_ID = "severityId"
        const val KEY_SHOW_DIALOG = "show_dialog_key"
    }
}