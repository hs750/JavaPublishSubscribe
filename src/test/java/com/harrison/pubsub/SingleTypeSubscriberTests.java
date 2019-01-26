package com.harrison.pubsub;

import com.harrison.pubsub.examples.SingleTypeSubscriber;
import com.harrison.pubsub.helpers.TestingCallback;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SingleTypeSubscriberTests {

    @Test
    public void testCallbackCalledOnReceive(){
        TestingCallback callback = mock(TestingCallback.class);
        SingleTypeSubscriber sts = new SingleTypeSubscriber(callback);

        sts.receive("Test Data");

        verify(callback, times(1)).callback("Test Data");
    }
}