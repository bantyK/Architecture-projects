package dagger;

import main.MainActivity;

@Component(modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity target);
}
