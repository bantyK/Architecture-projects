package main;

import deeplink.DeeplinkPayloadHandler;

public class MainActivity implements PayloadListener {
    public static void main(String[] args) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.start();
    }

    private void start() {
        DeeplinkPayloadHandler deeplinkPayloadHandler = new DeeplinkPayloadHandler(this);
        deeplinkPayloadHandler.init();
    }

    @Override
    public void transferPayload(String payload) {
        System.out.printf("Payload Received in Main Activity : %s\n", payload);
    }
}
