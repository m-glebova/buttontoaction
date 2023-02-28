package com.magl.buttontoaction.core.action

import com.magl.buttontoaction.core.model.Action
import com.magl.buttontoaction.core.model.ActionType
import com.magl.buttontoaction.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.DayOfWeek
import kotlin.test.assertEquals

class GetPrioritisedActionsUseCaseTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val actionRepository = FakeActionsDataSource()
    private val useCaseUnderTest = LoadPrioritisedActionsUseCase(actionRepository)
    private val expectedAction = Action(
        ActionType.TOAST,
        true,
        1,
        listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        ),
        1000,
        null
    )

    @Test
    fun testEnabledActions() {
        runTest(dispatcher) {
            val result = useCaseUnderTest.execute()
            assertEquals(listOf(expectedAction), result)
        }
    }

    class FakeActionsDataSource : ActionRepository {
        override suspend fun fetchActions() = Result.Success(Unit)

        override suspend fun saveLastTimeActionUsed(
            actionType: ActionType,
            millis: Long,
        ): Result<Unit> = Result.Success(Unit)

        override fun hasActions(): Flow<Boolean> = flowOf(true)

        override suspend fun loadActions(): List<Action> {
            return listOf(
                Action(
                    ActionType.ANIMATION,
                    false,
                    1,
                    listOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY),
                    1000,
                    null
                ),
                Action(
                    ActionType.TOAST,
                    true,
                    1,
                    listOf(
                        DayOfWeek.MONDAY,
                        DayOfWeek.TUESDAY,
                        DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY,
                        DayOfWeek.FRIDAY,
                        DayOfWeek.SATURDAY,
                        DayOfWeek.SUNDAY
                    ),
                    1000,
                    null
                )
            )
        }
    }
}
