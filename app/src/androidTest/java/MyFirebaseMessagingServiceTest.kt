package com.example.dealseekerapplication





import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyFirebaseMessagingServiceTest {

    @Mock
    lateinit var notificationManager: NotificationManager

    @Mock
    lateinit var remoteMessage: RemoteMessage

    @Mock
    lateinit var context: Context

    @Captor
    lateinit var notificationCaptor: ArgumentCaptor<Notification>

    private lateinit var service: MyFirebaseMessagingService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        service = Mockito.spy(MyFirebaseMessagingService())
        Mockito.`when`(service.getSystemService(Context.NOTIFICATION_SERVICE)).thenReturn(notificationManager)
        Mockito.`when`(context.packageName).thenReturn("com.example.app")
    }
    @Test
    fun testOnMessageReceived_withNotification() {
        val notification = Mockito.mock(RemoteMessage.Notification::class.java)
        Mockito.`when`(remoteMessage.notification).thenReturn(notification)
        Mockito.`when`(notification.title).thenReturn("Test Title")
        Mockito.`when`(notification.body).thenReturn("Test Body")

        service.onMessageReceived(remoteMessage)

        Mockito.verify(service).sendNotification("Test Title", "Test Body")
    }

    @Test
    fun testOnMessageReceived_withData() {
        service.onMessageReceived(remoteMessage)

        Mockito.verify(service).handleData(mapOf("target_activity" to "MainActivity"))
    }

    @Test
    fun testSendNotification() {
        val testIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, testIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        service.sendNotification("Test Title", "Test Body")

        Mockito.verify(notificationManager).notify(Mockito.anyInt(), notificationCaptor.capture())
        val notification = notificationCaptor.value

        assert(notification.extras.getString(NotificationCompat.EXTRA_TITLE) == "Test Title")
        assert(notification.extras.getString(NotificationCompat.EXTRA_TEXT) == "Test Body")
    }
    @Test
    fun testSendRegistrationToServer() {
        service.sendRegistrationToServer("dummy_token")
        Mockito.verify(service).sendRegistrationToServer("dummy_token")
    }
}
