package ru.cometrica.demoapp.presentation.document.presenter

import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import ru.cometrica.demoapp.domain.author.GetCurrentAuthor
import ru.cometrica.demoapp.domain.document.StreamDocumentList
import ru.cometrica.demoapp.domain.document.SyncDocumentList
import ru.cometrica.demoapp.domain.github.FindGitHubRepo
import ru.cometrica.demoapp.domain.github.FindGitHubRepoParam
import ru.cometrica.demoapp.domain.location.StreamCurrentLocation
import ru.cometrica.demoapp.presentation.BasePresenter
import ru.cometrica.demoapp.presentation.document.model.DocumentViewModel
import ru.cometrica.demoapp.presentation.document.view.DocumentListView
import java.util.concurrent.TimeUnit

class DocumentListPresenter(
    private val getDocumentList: StreamDocumentList,
    private val syncDocumentList: SyncDocumentList,
    private val streamCurrentLocation: StreamCurrentLocation,
    private val findGitHubRepo: FindGitHubRepo,
    getCurrentAuthor: GetCurrentAuthor
) : BasePresenter<DocumentListView>() {

    private var disposables = CompositeDisposable()
    private var authorIdSubject = BehaviorSubject.create<Long>()
    private var rxAuthorId: Observable<Long> = authorIdSubject.hide()
        .switchIfEmpty(
            // try to get current authorId, do nothing if empty
            getCurrentAuthor
                .build()
                .toObservable()
        )

    override fun onAttachView(view: DocumentListView) {
        super.onAttachView(view)
        disposables += subscribeRefreshClick()
        disposables += subscribeAuthorIdTextChanges()
        disposables += subscribeDocumentChanges()
        disposables += subscribeLocation()
        disposables += findRepo() // FIXME remove
    }

    private fun findRepo() =
        findGitHubRepo
            .build(FindGitHubRepoParam("DemoApp", "danelyan"))
            .subscribe(
                { Log.d("!!!", it.toString()) },
                { Log.e("!!!", "", it) }
            )

    override fun onDetachView() {
        super.onDetachView()
        disposables.dispose()
    }

    private fun subscribeRefreshClick() =
        Observables.combineLatest(getViewOrThrow().refreshClick(), rxAuthorId)
            .flatMapSingle { (_, authorId) ->
                syncDocumentList
                    .build(authorId)
                    .toSingleDefault(authorId)
            }
            .subscribe(
                { Log.i("DocumentListPresenter", "Documents for author $it synced") },
                { error -> Log.e("DocumentListPresenter", "Error syncing docs on click", error) }
            )

    private fun subscribeDocumentChanges() =
        rxAuthorId
            .flatMap { getDocumentList.build(it) }
            .map { documents ->
                documents.map {
                    DocumentViewModel("${it.author.name} ${it.author.surname}", it.name)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view?.showDocuments(it) },
                { view?.showDocumentListError(it) }
            )

    private fun subscribeAuthorIdTextChanges() =
        getViewOrThrow().authorIdFieldChange()
            .debounce(DEBOUNCE_TYPING_DELAY, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .map { it.toLongOrNull() ?: -1 }
            .subscribe(
                {
                    if (it < 0) view?.showAuthorIdError()
                    else authorIdSubject.onNext(it)
                },
                {
                    it.printStackTrace()
                    view?.showSomeError()
                })

    private fun subscribeLocation() =
        streamCurrentLocation.build()
            .map { it.address }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view?.showAddress(it) }


    companion object {
        const val DEBOUNCE_TYPING_DELAY = 200L
    }

}
