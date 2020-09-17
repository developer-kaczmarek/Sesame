package me.aartikov.lib.loading.paged

import me.aartikov.lib.loading.paged.PagedLoading.State


data class PagedLoadingUiState<T>(
    val data: List<T>,
    val error: Throwable?,
    val emptyVisible: Boolean,
    val loadingVisible: Boolean,
    val refreshVisible: Boolean,
    val refreshEnabled: Boolean,
    val loadMoreVisible: Boolean,
    val loadMoreEnabled: Boolean
)

val <T> State<T>.uiState: PagedLoadingUiState<T>
    get() = PagedLoadingUiState(
        data = when (this) {
            is State.Data -> this.data
            is State.Refresh -> this.data
            is State.NewPageLoading -> this.data
            is State.FullData -> this.data
            else -> emptyList()
        },
        error = when (this) {
            is State.EmptyError -> this.throwable
            else -> null
        },
        emptyVisible = this is State.Empty,
        loadingVisible = this is State.EmptyLoading,
        refreshVisible = this is State.Refresh,
        refreshEnabled = this is State.Data || this is State.Refresh
                || this is State.NewPageLoading || this is State.FullData,
        loadMoreVisible = this is State.NewPageLoading,
        loadMoreEnabled = this is State.Data || this is State.NewPageLoading

    )

fun <T> PagedLoadingUiState<T>.setToView(
    setData: (List<T>) -> Unit = {},
    setDataVisible: (Boolean) -> Unit = {},
    setError: (Throwable) -> Unit = {},
    setErrorVisible: (Boolean) -> Unit = {},
    setEmptyVisible: (Boolean) -> Unit = {},
    setLoadingVisible: (Boolean) -> Unit = {},
    setRefreshVisible: (Boolean) -> Unit = {},
    setRefreshEnabled: (Boolean) -> Unit = {},
    setLoadMoreVisible: (Boolean) -> Unit = {},
    setLoadMoreEnabled: (Boolean) -> Unit = {}
) {

    if (data.isNotEmpty()) {
        setDataVisible(true)
        setData(data)
    } else {
        setDataVisible(false)
    }

    if (error != null) {
        setErrorVisible(true)
        setError(error)
    } else {
        setErrorVisible(false)
    }

    setEmptyVisible(emptyVisible)
    setLoadingVisible(loadingVisible)
    setRefreshVisible(refreshVisible)
    setRefreshEnabled(refreshEnabled)
    setLoadMoreVisible(loadMoreVisible)
    setLoadMoreEnabled(loadMoreEnabled)
}