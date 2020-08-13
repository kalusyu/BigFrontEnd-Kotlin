package com.kalusyu.hiltpractice.byhand

import retrofit2.Retrofit

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/8/11 9:38
 *
 **/
class AppContainer {
    private val retrofit = Retrofit.Builder().baseUrl("").build().create(LoginService::class.java)
    private val remoteDataSource =
        UserRemoteDataSource(retrofit)
    private val localDataSource =
        UserLocalDataSource()
    val userRepository = UserRepository(
        remoteDataSource,
        localDataSource
    )

    val loginViewModel = LoginViewModelFactory(
        userRepository
    ).create()

}

interface Factory<T> {
    fun create(): T
}

class LoginViewModelFactory(private val userRepository: UserRepository):
    Factory<LoginViewModel> {

    override fun create(): LoginViewModel {
        return LoginViewModel(userRepository)
    }
}