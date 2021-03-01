package com.workshop.github.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.workshop.github.data.local.FavoriteUserDao
import com.workshop.github.data.local.UserDatabase

class UserContentProvider : ContentProvider() {

    companion object{
        const val AUTHORITY = "com.workshop.github"
        const val TABLE_NAME = "favorite_user"
        const val ID_FAVORITE_USER_DATA = 1
        val uriMath = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMath.addURI(AUTHORITY, TABLE_NAME, ID_FAVORITE_USER_DATA)
        }
    }

    private lateinit var userDao: FavoriteUserDao

    override fun onCreate(): Boolean {
        userDao = context?.let {
            UserDatabase.getDatabase(it)?.favoriteUserDataDao()
        }!!

        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor?
        when(uriMath.match(uri)){
            ID_FAVORITE_USER_DATA -> {
                cursor = userDao.findAll()
                if (context != null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> cursor = null
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = 0
}