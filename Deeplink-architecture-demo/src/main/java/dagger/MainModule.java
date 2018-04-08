package dagger;

import deeplink.DeeplinkPayloadHandler;
import main.MainActivity;
import main.PayloadListener;

@Module
public class MainModule {
    @Provides
    public DeeplinkPayloadHandler provideDeeplinkPayloadHandler(PayloadListener listener) {
        return new DeeplinkPayloadHandler(listener);
    }

    @Provides
    public PayloadListener providePayloadListener() {
        return new MainActivity();
    }
}
