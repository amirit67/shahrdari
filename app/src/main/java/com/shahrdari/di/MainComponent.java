package com.shahrdari.di;

import com.shahrdari.activity.MainActivity;
import com.shahrdari.remote.repository.RemoteRepository;
import com.shahrdari.utils.Networking.ConnectionBuddy;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@MainScope
@Component(modules = {AppModule.class, NetModule.class})
public interface MainComponent {

    void Inject(RemoteRepository remoteRepository);


    void Inject(ConnectionBuddy connectionBuddy);

    void Inject(MainActivity mainActivity);



}
