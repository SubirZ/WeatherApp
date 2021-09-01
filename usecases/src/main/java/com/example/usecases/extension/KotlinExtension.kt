package com.example.usecases.extension

inline fun <T, R, S, Y> safeLet(
    first: T?,
    second: R?,
    third: S?,
    block: (T, R, S) -> Y
) {
    if (first != null && second != null && third != null) {
        block(first, second, third)
    }
}

inline fun <A, B, C, D, E, F, G, H, I, J, K, L, S> safeLetMultiple(
    first: A?,
    second: B?,
    third: C?,
    fourth: D?,
    fifth: E?,
    sixth: F?,
    seventh: G?,
    eighth: H?,
    ninth: I?,
    tenth: J?,
    eleventh: K?,
    twelfth: L?,
    block: (A, B, C, D, E, F, G, H, I, J, K, L) -> S
) {
    if (first != null && second != null && third != null && fourth != null && fifth != null && sixth != null &&
        seventh != null && eighth != null && ninth != null && tenth != null && eleventh != null && twelfth != null
    ) {
        block(
            first,
            second,
            third,
            fourth,
            fifth,
            sixth,
            seventh,
            eighth,
            ninth,
            tenth,
            eleventh,
            twelfth
        )
    }
}

fun Int.isNegative() = this < 0