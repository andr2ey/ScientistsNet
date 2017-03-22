package util;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * Created on 20.03.2017.
 */
public abstract class AsyncListenerAdapter implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {

    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {

    }

    @Override
    public void onError(AsyncEvent event) throws IOException {

    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {

    }
}
