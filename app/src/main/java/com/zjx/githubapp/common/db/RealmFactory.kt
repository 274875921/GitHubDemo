package com.zjx.githubapp.common.db

import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 *created by zjx
 * on 2019/12/22 0022
 */
class RealmFactory private constructor()
{
    companion object{
        private var mRealmFactory:RealmFactory ?= null

        val instance : RealmFactory
        get() {
            if(mRealmFactory == null)
            {
                synchronized(RealmFactory::class.java)
                {
                    if (mRealmFactory == null)
                    {
                        mRealmFactory = RealmFactory()
                    }
                }
            }
            return mRealmFactory!!
        }

        fun getRealmObservable():Observable<Realm>
        {
            return Observable.create {
                val realm = Realm.getDefaultInstance()
                it.onNext(realm)
                it.onComplete()
            }
        }
    }

    init {
        val realmConfiguration = RealmConfiguration
            .Builder()
            .name("github.db")
            .schemaVersion(1)
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}