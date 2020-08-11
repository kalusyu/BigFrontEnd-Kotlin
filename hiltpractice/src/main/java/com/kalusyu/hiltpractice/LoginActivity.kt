package com.kalusyu.hiltpractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 9:08
 *
 **/
class LoginActivity: AppCompatActivity() {

    private lateinit var loginViewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 手动创建并注入各个依赖想
//        val retrofit = Retrofit.Builder().baseUrl("").build().create(LoginService::class.java)
//
//        val remoteDataSource = UserRemoteDataSource(retrofit)
//        val localDataSource = UserLocalDataSource()
//        val userRepository = UserRepository(remoteDataSource, localDataSource)
//
//        loginViewModel = LoginViewModel(userRepository)

        // 创建自己的容器依赖
        val appContainer = (application as MyApplication).appContainer
        // loginViewModel = LoginViewModel(appContainer.userRepository)
        loginViewModel = appContainer.loginViewModel

    }
}