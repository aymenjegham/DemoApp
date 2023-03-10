package com.aymen.testapp.ui.utils

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

inline fun CombinedLoadStates.decideOnState(
    showLoading: (Boolean) -> Unit,
    showEmptyState: (Boolean) -> Unit,
    showError: (String) -> Unit,
    itemCount: Int,
) {
    showLoading(refresh is LoadState.Loading)

    showEmptyState(
        source.append is LoadState.NotLoading
                && source.append.endOfPaginationReached
                && itemCount == 0
    )


    val errorState = source.append as? LoadState.Error
        ?: source.prepend as? LoadState.Error
        ?: source.refresh as? LoadState.Error
        ?: append as? LoadState.Error
        ?: prepend as? LoadState.Error
        ?: refresh as? LoadState.Error

    errorState?.let { showError(it.error.toString()) }
}