package deeplink.appsflyer;

import deeplink.DeeplinkDataSource;
import deeplink.PayloadHandler;

import java.util.Timer;
import java.util.TimerTask;

public class AppsflyerDeeplink implements DeeplinkDataSource {

    PayloadHandler handler;

    public AppsflyerDeeplink(PayloadHandler payloadHandler) {
        this.handler = payloadHandler;
    }


    @Override
    public void onDataReceived(String payload) {
        handler.onPayloadReceived(payload);
    }

    public void callbackReceivedFromAppsflyer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                onDataReceived(AppsflyerDeeplink.class.getSimpleName() + "/play/cid=12345678/pid=playlist-1234");
            }
        }, 2000);
    }

}
