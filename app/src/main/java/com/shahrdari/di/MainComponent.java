package com.shahrdari.di;

import com.shahrdari.UpdateCheckerAsynkTask;
import com.shahrdari.activity.MainActivity;
import com.shahrdari.remote.repository.RemoteRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@MainScope
@Component(modules = {AppModule.class, NetModule.class})
public interface MainComponent {

    void Inject(RemoteRepository remoteRepository);

    void Inject(UpdateCheckerAsynkTask updateCheckerAsynkTask);

    void Inject(MainActivity mainActivity);


}
