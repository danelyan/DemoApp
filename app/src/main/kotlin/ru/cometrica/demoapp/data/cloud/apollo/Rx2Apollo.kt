package ru.cometrica.demoapp.data.cloud.apollo

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloPrefetch
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.internal.util.Cancelable
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions

fun ApolloPrefetch.toCompletable(): Completable {
    return Completable.create { emitter ->
        cancelOnCompletableDisposed(emitter, this)
        this.enqueue(object : ApolloPrefetch.Callback() {
            override fun onSuccess() {
                if (!emitter.isDisposed) {
                    emitter.onComplete()
                }
            }

            override fun onFailure(e: ApolloException) {
                Exceptions.throwIfFatal(e)
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        })
    }
}

fun <T> ApolloCall<T>.toSingle(): Single<Response<T>> =
    Single.create { emitter ->
        cancelOnObservableDisposed(emitter, this)
        this.enqueue(object : ApolloCall.Callback<T>() {

            override fun onResponse(response: Response<T>) {
                if (!emitter.isDisposed) {
                    emitter.onSuccess(response)
                }
            }

            override fun onFailure(e: ApolloException) {
                Exceptions.throwIfFatal(e)
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }

        })
    }

private fun cancelOnCompletableDisposed(emitter: CompletableEmitter, cancelable: Cancelable) {
    emitter.setDisposable(getRx2Disposable(cancelable))
}

private fun <T> cancelOnObservableDisposed(emitter: SingleEmitter<T>, cancelable: Cancelable) {
    emitter.setDisposable(getRx2Disposable(cancelable))
}

private fun getRx2Disposable(cancelable: Cancelable): Disposable {
    return object : Disposable {
        override fun dispose() {
            cancelable.cancel()
        }

        override fun isDisposed(): Boolean {
            return cancelable.isCanceled
        }
    }
}
