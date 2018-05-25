package com.isdl.spajam2019.Main;

import android.util.Log;

import com.isdl.spajam2019.Models.QiitaItem;
import com.isdl.spajam2019.Services.QiitaApiService;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by takayayuuki on 2018/05/25.
 */

public class MainPresenter {
    QiitaApiService qiitaApiService;

    @Inject
    public MainPresenter(QiitaApiService qiitaApiService) {
        this.qiitaApiService = qiitaApiService;
    }

    public void apiRequest() {
        qiitaApiService.items()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<QiitaItem>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("QiitaItem:", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("error:", e.toString());
                    }

                    @Override
                    public void onNext(List<QiitaItem> qiitaItems) {
                        Log.d("QiitaItem:", "onNext");

                    }
                });
    }


}

