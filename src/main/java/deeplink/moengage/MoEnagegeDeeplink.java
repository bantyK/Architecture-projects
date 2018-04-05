package deeplink.moengage;

import deeplink.DeeplinkDataSource;
import deeplink.PayloadHandler;

import java.util.Timer;
import java.util.TimerTask;

public class MoEnagegeDeeplink implements DeeplinkDataSource {

    private final PayloadHandler handler;

    public MoEnagegeDeeplink(PayloadHandler payloadListener) {
        this.handler = payloadListener;

        callbackReceivedFromMoengage();
    }

    @Override
    public void onDataReceived(String payload) {
        handler.onPayloadReceived(payload);
    }

    private void callbackReceivedFromMoengage() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                onDataReceived(MoEnagegeDeeplink.class.getSimpleName() + "/play/cid=12345678/pid=playlist-1234");
            }
        }, 4000);
    }
}
