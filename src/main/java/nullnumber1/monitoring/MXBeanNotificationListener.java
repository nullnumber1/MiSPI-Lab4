package nullnumber1.monitoring;

import lombok.extern.slf4j.Slf4j;

import javax.management.Notification;
import javax.management.NotificationFilter;
import javax.management.NotificationFilterSupport;
import javax.management.NotificationListener;

@Slf4j
public class MXBeanNotificationListener implements NotificationListener {
    private final String notificationType;

    public MXBeanNotificationListener(String notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        log.info(String.format("Listener %s: %s", notificationType, notification.getMessage()));
    }

    public NotificationFilter getNotificationFilter() {
        NotificationFilterSupport filter = new NotificationFilterSupport();
        filter.enableType(notificationType);
        return filter;
    }
}
