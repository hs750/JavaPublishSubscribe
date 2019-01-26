package com.harrison.pubsub;

import com.harrison.pubsub.examples.PublishSubscriber;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PublishSubscribeManagerTests {

    @Test
    public void testBroadcastNoSubscribers(){
        Publisher publisher = mock(Publisher.class);

        PublishSubscribeManager<String> psm = new PublishSubscribeManager<>(String.class);

        psm.broadcastData("Test Data", publisher);

        verify(publisher, times(0)).isPublishLoopbackAllowed();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBroadcastOneSubscriber(){
        Publisher publisher = mock(Publisher.class);
        Subscriber<String> subscriber = mock(Subscriber.class);

        PublishSubscribeManager<String> psm = new PublishSubscribeManager<>(String.class);
        psm.addSubscriber(subscriber);

        psm.broadcastData("Test Data", publisher);

        verify(publisher, times(0)).isPublishLoopbackAllowed();
        verify(subscriber, times(1)).receive("Test Data");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBroadcastTwoSubscribers(){
        Publisher publisher = mock(Publisher.class);
        Subscriber<String> subscriber1 = mock(Subscriber.class);
        Subscriber<String> subscriber2 = mock(Subscriber.class);

        PublishSubscribeManager<String> psm = new PublishSubscribeManager<>(String.class);
        psm.addSubscriber(subscriber1);
        psm.addSubscriber(subscriber2);

        psm.broadcastData("Test Data", publisher);

        verify(publisher, times(0)).isPublishLoopbackAllowed();
        verify(subscriber1, times(1)).receive("Test Data");
        verify(subscriber2, times(1)).receive("Test Data");
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBroadcastSubscriberNoLoopback(){
        PublishSubscriber<String> publishSubscriber = mock(PublishSubscriber.class);
        when(publishSubscriber.isPublishLoopbackAllowed()).thenReturn(false);

        PublishSubscribeManager<String> psm = new PublishSubscribeManager<>(String.class);
        psm.addSubscriber(publishSubscriber);

        psm.broadcastData("Test Data", publishSubscriber);

        verify(publishSubscriber, times(1)).isPublishLoopbackAllowed();
        verify(publishSubscriber, times(0)).receive(anyString());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testBroadcastSubscriberLoopback(){
        PublishSubscriber<String> publishSubscriber = mock(PublishSubscriber.class);
        when(publishSubscriber.isPublishLoopbackAllowed()).thenReturn(true);

        PublishSubscribeManager<String> psm = new PublishSubscribeManager<>(String.class);
        psm.addSubscriber(publishSubscriber);

        psm.broadcastData("Test Data", publishSubscriber);

        verify(publishSubscriber, times(1)).isPublishLoopbackAllowed();
        verify(publishSubscriber, times(1)).receive("Test Data");
    }

}
