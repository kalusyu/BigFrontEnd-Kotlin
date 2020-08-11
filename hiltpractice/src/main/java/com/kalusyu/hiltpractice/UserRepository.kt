package com.kalusyu.hiltpractice

/**
 * desc: 
 *
 * @author biaowen.yu
 * @date 2020/8/11 9:25
 *
 **/
class UserRepository(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) {
}