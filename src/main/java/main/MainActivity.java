package main;

import dagger.DaggerMainComponent;
import deeplink.DeeplinkPayloadHandler;

import javax.inject.Inject;

public class MainActivity implements PayloadListener {

    @Inject
    DeeplinkPayloadHandler deeplinkPayloadHandler;

    public static void main(String[] args) {
        new MainActivity().start();
    }

    private void start() {
        DeeplinkPayloadHandler deeplinkPayloadHandler = DaggerMainComponent.builder().build().inject();
        deeplinkPayloadHandler.init();
    }

    @Override
    public void transferPayload(String payload) {
        System.out.printf("Payload Received in Main Activity : %s\n", payload);
    }
}
