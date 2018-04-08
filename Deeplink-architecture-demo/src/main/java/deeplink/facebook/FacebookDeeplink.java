package deeplink.facebook;

import deeplink.DeeplinkDataSource;
import deeplink.PayloadHandler;

import java.util.Timer;
import java.util.TimerTask;

public class FacebookDeeplink implements DeeplinkDataSource {

    private final PayloadHandler handler;

    public FacebookDeeplink(PayloadHandler payloadListener) {
        this.handler = payloadListener;
    }

    public void callbackReceivedFromFacebook() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                onDataReceived(FacebookDeeplink.class.getSimpleName() + "/play/cid=12345678/pid=playlist-1234");
            }
        }, 3000);
    }

    @Override
    public void onDataReceived(String payload) {
        handler.onPayloadReceived(payload);
    }
}
