package com.kalusyu.hiltpractice.hilt

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/15 12:49
 *
 **/
@Entity
class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val updateTime: Long = System.currentTimeMillis()
)