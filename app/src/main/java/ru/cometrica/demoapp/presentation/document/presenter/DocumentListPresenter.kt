package ru.cometrica.demoapp.presentation.document.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.subjects.BehaviorSubject
import ru.cometrica.demoapp.domain.author.AuthorInteractor
import ru.cometrica.demoapp.domain.document.StreamDocumentListInteractor
import ru.cometrica.demoapp.domain.document.SyncDocumentListInteractor
import ru.cometrica.demoapp.domain.location.CurrentLocationInteractor
import ru.cometrica.demoapp.presentation.BasePresenter
import ru.cometrica.demoapp.presentation.document.model.DocumentViewModel
import ru.cometrica.demoapp.presentation.document.view.IDocumentListView
import java.util.concurrent.TimeUnit

class DocumentListPresenter(
    private val view: IDocumentListView,
    private val getDocumentListInteractor: StreamDocumentListInteractor,
    private val syncDocumentListInteractor: SyncDocumentListInteractor,
    private val currentLocationInteractor: CurrentLocationInteractor,
    authorInteractor: AuthorInteractor
) : BasePresenter() {

    private var disposables = CompositeDisposable()
    private var authorIdSubject = BehaviorSubject.create<Long>()
    private var rxAuthorId = authorIdSubject.hide()
        .switchIfEmpty(
            // try to get current authorId, do nothing if empty
            authorInteractor
                .getCurrentAuthorId()
                .toObservable()
        )

    override fun onInit() {
        disposables += subscribeRefreshClick()
        disposables += subscribeAuthorIdTextChanges()
        disposables += subscribeDocumentChanges()
        disposables += subscribeLocation()
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    private fun subscribeRefreshClick() =
        Observables.combineLatest(view.refreshClick(), rxAuthorId)
            .flatMapSingle { (_, authorId) ->
                syncDocumentListInteractor
                    .syncDocumentList(authorId)
                    .toSingleDefault(authorId)
            }
            .subscribe(
                { Log.i("DocumentListPresenter", "Documents for author $it synced") },
                { error -> Log.e("DocumentListPresenter", "Error syncing docs on click", error) }
            )

    private fun subscribeDocumentChanges() =
        rxAuthorId
            .flatMap { getDocumentListInteractor.streamDocumentList(it) }
            .map { documents ->
                documents.map {
                    DocumentViewModel("${it.author.name} ${it.author.surname}", it.name)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { view.showDocuments(it) },
                { view.showDocumentListError(it) }
            )

    private fun subscribeAuthorIdTextChanges() =
        view.authorIdFieldChange()
            .debounce(DEBOUNCE_TYPING_DELAY, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .map { it.toLongOrNull() ?: -1 }
            .subscribe(
                {
                    if (it < 0) view.showAuthorIdError()
                    else authorIdSubject.onNext(it)
                },
                {
                    it.printStackTrace()
                    view.showSomeError()
                })

    private fun subscribeLocation() =
        currentLocationInteractor.streamLocation()
            .map { it.address }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { view.showAddress(it) }


    companion object {
        const val DEBOUNCE_TYPING_DELAY = 200L
    }

}
