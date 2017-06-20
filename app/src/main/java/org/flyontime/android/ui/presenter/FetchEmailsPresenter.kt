package org.flyontime.android.ui.presenter


import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.gmail.GmailScopes
import org.flyontime.android.FlyOnTimeApplication
import org.flyontime.android.model.service.FlyOnTimeAPI
import org.flyontime.android.scheduler.SchedulerProvider
import org.flyontime.android.ui.contract.FetchEmailsViewPresenterContract
import org.flyontime.android.ui.contract.FetchEmailsViewPresenterContract.FetchEmailsPresenterActions
import javax.inject.Inject


class FetchEmailsPresenter : FetchEmailsPresenterActions {

    private val TAG = this.javaClass.simpleName

    private val SCOPES: List<String> = listOf(GmailScopes.GMAIL_READONLY)

    @Inject
    internal lateinit var api: FlyOnTimeAPI

    @Inject
    internal lateinit var schedulerProvider: SchedulerProvider

    @Inject
    internal lateinit var interactor: HomeInteractor

    private var mCredential: GoogleAccountCredential

    private var view: FetchEmailsViewPresenterContract.FetchEmailsViewActions

    constructor(view: FetchEmailsViewPresenterContract.FetchEmailsViewActions) {
        this.view = view
        FlyOnTimeApplication.getApplication().component.inject(this)
        mCredential = GoogleAccountCredential
                .usingOAuth2(FlyOnTimeApplication.getApplication(), SCOPES)
                .setBackOff(ExponentialBackOff())

    }

    constructor(view: FetchEmailsViewPresenterContract.FetchEmailsViewActions, schedulerProvider: SchedulerProvider, api: FlyOnTimeAPI) {
        this.view = view
        this.interactor = HomeInteractor(api, schedulerProvider)
        this.api = api
        mCredential = GoogleAccountCredential
                .usingOAuth2(FlyOnTimeApplication.getApplication(), SCOPES)
                .setBackOff(ExponentialBackOff())

    }

    override fun searchMessages() {

    }


}
