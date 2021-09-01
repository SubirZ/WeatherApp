package com.example.usecases

interface BaseUseCase<out Result, in ExecutableParam> {

    /**
     * Perform an operation with no input parameters.
     * Will throw an exception by default, if not implemented but invoked.
     *
     * @return
     */
    suspend fun perform(): Result= throw NotImplementedError()

    /**
     * Perform an operation.
     *  Will throw an exception by default, if not implemented but invoked.
     *
     * @param param
     * @return
     */
    suspend fun perform(param: ExecutableParam): Result = throw NotImplementedError()
}