package deeplink;

import deeplink.appsflyer.AppsflyerDeeplink;
import deeplink.facebook.FacebookDeeplink;
import deeplink.moengage.MoEnagegeDeeplink;
import main.PayloadListener;

public class DeeplinkPayloadHandler implements PayloadHandler {
    PayloadListener listener;
    private AppsflyerDeeplink appsflyer;
    private MoEnagegeDeeplink moEnagegeDeeplink;
    private FacebookDeeplink facebookDeeplink;


    public DeeplinkPayloadHandler(PayloadListener listener) {
        this.listener = listener;
    }

    public void init() {
        initAppsflyer();
        initMoEnagee();
        initFacebook();
    }

    public void initAppsflyer() {
        appsflyer = new AppsflyerDeeplink(this);
        appsflyer.callbackReceivedFromAppsflyer();
    }

    public void initMoEnagee() {
        moEnagegeDeeplink = new MoEnagegeDeeplink(this);
        moEnagegeDeeplink.callbackReceivedFromMoengage();
    }

    public void initFacebook() {
        facebookDeeplink = new FacebookDeeplink(this);
        facebookDeeplink.callbackReceivedFromFacebook();
    }

    @Override
    public void onPayloadReceived(String payload) {
        listener.transferPayload(payload);
    }
}
