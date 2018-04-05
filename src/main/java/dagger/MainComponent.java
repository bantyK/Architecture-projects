package dagger;

import deeplink.DeeplinkPayloadHandler;

@Component(modules = {MainModule.class})
public interface MainComponent {
    DeeplinkPayloadHandler inject();
}
