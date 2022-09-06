package com.sebqv97.myapplication.feature_users.presentation.user_list
//
//import com.sebqv97.myapplication.feature_users.data.repository.FakeUsersRepository
//import com.sebqv97.myapplication.feature_users.domain.use_case.GetUsersUseCase
//import org.junit.Assert.*
//import org.junit.Before
//import org.mockito.Mock
//
//class UserListViewModelTest{
//
//
//    private lateinit var getUsersUseCase: GetUsersUseCase
//
//    private lateinit var userListViewModel : UserListViewModel
//
//    @Mock
//    private lateinit var fakeUsersRepository: FakeUsersRepository
//
//
//    @Before
//    fun setUp() {
//        getUsersUseCase = GetUsersUseCase(fakeUsersRepository)
//
//    }
//
////    @Test
////    fun `gdfsg`() {
////        runBlocking {
////            userListViewModel = UserListViewModel(getUsersUseCase)
////            val state = userListViewModel.state.value
////        }
////    }
//}