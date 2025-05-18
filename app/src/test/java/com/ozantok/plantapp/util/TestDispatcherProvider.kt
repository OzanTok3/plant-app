package com.ozantok.plantapp.util

import com.ozantok.core.util.DispatcherProvider
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcherProvider : DispatcherProvider {
    override val main = UnconfinedTestDispatcher()
    override val io = UnconfinedTestDispatcher()
    override val default = UnconfinedTestDispatcher()
    override val unconfined = UnconfinedTestDispatcher()
}