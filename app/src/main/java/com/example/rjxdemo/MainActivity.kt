package com.example.rjxdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.rjxdemo.model.DataSource
import com.example.rjxdemo.model.Task
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var taskObservable: Observable<Task> = Observable.fromIterable(DataSource.createTaskList())
            .subscribeOn(Schedulers.io())       // to process on background thread
            .filter(object : Predicate<Task> {                  // operator to filter out certain task
                override fun test(t: Task): Boolean {
                    d(TAG,"${Thread.currentThread().name}")
                    Thread.sleep(1000)
                    return t.isComplete
                }

            }).observeOn(AndroidSchedulers.mainThread())        // observer observing on main thread

        taskObservable.subscribe(object : Observer<Task> {
            override fun onComplete() {
                d(TAG,"onComplete called")
            }

            override fun onSubscribe(d: Disposable) {
                d(TAG,"OnSubscribe Called.")
                disposable.add(d)
            }

            override fun onNext(t: Task) {
                d(TAG,"onNext: ${Thread.currentThread().name}")
                d(TAG,"${t.description}")
                Thread.sleep(1000)
            }

            override fun onError(e: Throwable) {
                d(TAG,"onError: ${e.printStackTrace()}")
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}
