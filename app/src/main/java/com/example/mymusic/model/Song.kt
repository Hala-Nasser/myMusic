package com.example.mymusic.model

import android.os.Parcel
import android.os.Parcelable
import com.example.mymusic.R

data class Song (var id:Int,var songName:String?,var songAdder:String?,var songType:String?,var songWord:String?,var songImage:String?, var isFavorite:String?, var counter:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()

    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(songName)
        parcel.writeString(songAdder)
        parcel.writeString(songType)
        parcel.writeString(songWord)
        parcel.writeString(songImage)
        parcel.writeString(isFavorite)
        parcel.writeInt(counter)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }



        val COL_ID = "id"
        val COL_SONG_NAME = "songName"
        val COL_SONG_ADDER = "songAdder"
        val COL_SONG_TYPE="songType"
        val COL_SONG_WORD="songWord"
        val COL_SONG_IMAGE="songImage"
        val COL_IS_FAVORITE="isFavorite"
        val COL_COUNTER="counter"




        val TABLE_NAME = "Song"
        val TABLE_CREATE = "create table $TABLE_NAME ($COL_ID integer primary key autoincrement," +
                "$COL_SONG_NAME text not null, $COL_SONG_ADDER text,$COL_SONG_TYPE text not null,$COL_SONG_WORD text not null ,$COL_SONG_IMAGE text, $COL_IS_FAVORITE text DEFAULT 'false', $COL_COUNTER integer DEFAULT 0)"
    }



}