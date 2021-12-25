package com.example.mymusic.db

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.mymusic.model.Song

class DatabaseHelper(activity: Activity) :
    SQLiteOpenHelper(activity, DATABASE_NAME, null, DATABASE_VERSION){

    private val db: SQLiteDatabase = this.writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(Song.TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("Drop table if exists ${Song.TABLE_NAME}")
        onCreate(db)
    }

    fun insertSong(songName: String,songType: String,songAdder: String,songWord:String, songImage: String): Boolean {
        val cv = ContentValues()
        cv.put(Song.COL_SONG_NAME, songName)
        cv.put(Song.COL_SONG_TYPE, songType)
        cv.put(Song.COL_SONG_ADDER, songAdder)
        cv.put(Song.COL_SONG_WORD,songWord)
        cv.put(Song.COL_SONG_IMAGE,songImage)

        return db.insert(Song.TABLE_NAME, null, cv) > 0
    }


    fun getRomanticSongs(): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_SONG_TYPE}='Romantic' order by ${Song.COL_ID} asc ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),
                c.getString(4),c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun getTragedySongs(): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_SONG_TYPE}='Tragedy' order by ${Song.COL_ID} asc   ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun getNationalSongs(): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_SONG_TYPE}='National' order by ${Song.COL_ID} asc   ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun getDanceSongs(): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_SONG_TYPE}='Dance' order by ${Song.COL_ID} asc   ", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3),c.getString(4), c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun getFavorite(): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_IS_FAVORITE}='true' order by ${Song.COL_ID} asc   "
                , null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)
                ,c.getString(4), c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }

    fun search(search:String): ArrayList<Song> {
        val data = ArrayList<Song>()
        val c =
            db.rawQuery("select * from ${Song.TABLE_NAME} where ${Song.COL_SONG_NAME} like '%$search%' order by ${Song.COL_ID} asc "
                , null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            val s = Song(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)
                ,c.getString(4), c.getString(5),c.getString(6),c.getInt(7))
            data.add(s)
            c.moveToNext()
        }
        c.close()
        return data
    }


    fun deleteSong(id:Int) : Boolean{
        return db.delete(Song.TABLE_NAME,"${Song.COL_ID} = $id",null)>0
    }

    fun updateSong(oldId:Int, songName: String,songType: String,songWord: String,songImage: String) : Boolean{
        val cv = ContentValues()
        cv.put(Song.COL_SONG_NAME, songName)
        cv.put(Song.COL_SONG_TYPE, songType)
        cv.put(Song.COL_SONG_WORD, songWord)
        cv.put(Song.COL_SONG_IMAGE, songImage)

        return db.update(Song.TABLE_NAME,cv,"${Song.COL_ID} = $oldId",null)>0
    }

     fun favorite(oldId: Int, isFavorite: String, counter:Int): Boolean{
        val cv = ContentValues()
        cv.put(Song.COL_IS_FAVORITE, isFavorite)
         cv.put(Song.COL_COUNTER, counter)
         return db.update(Song.TABLE_NAME,cv,"${Song.COL_ID} = $oldId",null)>0
    }





    companion object {
        val DATABASE_NAME = "MyMusic"
        val DATABASE_VERSION = 1

    }
}

