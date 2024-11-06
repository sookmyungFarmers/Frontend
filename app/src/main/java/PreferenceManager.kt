//SharedPreferences를 사용하여 로그인 상태를 관리하는 유틸리티 클래스
package com.example.haeun

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val PREF_NAME = "user_prefs" // SharedPreferences 파일 이름
    private const val IS_LOGGED_IN = "is_logged_in" // 로그인 상태를 저장할 키

    // SharedPreferences 가져오기
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // 로그인 상태 저장
    // true일 경우 로그인 상태를 저장하며, false일 경우 비로그인 상태로 저장
    fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        val editor = getPreferences(context).edit()
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }

    // 로그인 상태 확인
    // 저장된 로그인 상태를 반환. 저장된 값이 없으면 기본값으로 false를 반환
    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(IS_LOGGED_IN, false)
    }

    // 로그아웃 시 호출하여 상태 초기화
    fun clearLoginStatus(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(IS_LOGGED_IN)
        editor.apply()
    }
}
