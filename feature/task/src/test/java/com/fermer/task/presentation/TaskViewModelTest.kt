package com.fermer.task.presentation


import app.cash.turbine.test
import com.fermer.task.data.FakeTaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import kotlin.test.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TaskViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: TaskViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val repository = FakeTaskRepository()
        viewModel = TaskViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `add task updates the tasks flow`() = runTest {
        viewModel.tasks.test {
            assertEquals(emptyList(), awaitItem()) // initial
            viewModel.addTask("Test 1")
            advanceUntilIdle()
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Test 1", result.first().title)
        }
    }

    @Test
    fun `remove task updates the tasks flow`() = runTest {
        viewModel.addTask("Test 2")
        advanceUntilIdle()
        val taskId = viewModel.tasks.value.first().id

        viewModel.removeTask(taskId)
        advanceUntilIdle()

        assertEquals(0, viewModel.tasks.value.size)
    }
}
