package com.workshop.customer

import android.database.Cursor

object MappingHelper {

    fun mapCurosorToArrayList(cursor: Cursor?): ArrayList<User> {
        val list = ArrayList<User>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.ID))
                val username =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.USERNAME))
                val avatar_url =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))

                list.add(
                    User(
                        username, id, avatar_url
                    )
                )
            }
        }
        return list
    }

}