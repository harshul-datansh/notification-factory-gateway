package com.gateway.notification.dto;


@lombok.Data
public class FireBaseTokenRequest {
    private FcmMessage message;

    @lombok.Data
    public static class FcmMessage {
        private String token;
        private Data data;
        private FcmApns apns;
        private FcmNotification notification;

        @lombok.Data
        public static class FcmNotification {
            private String title;
            private String body;

            @Override
            public String toString() {
                return "FcmAlert{"
                        + "body='" + body + '\''
                        + ", title='" + title + '\''
                        + '}';
            }
        }

        @lombok.Data
        public static class FcmApns {
            private FcmPayload payload;

            @lombok.Data
            public static class FcmPayload {
                private FcmAps aps;

                @lombok.Data
                public static class FcmAps {
                    private int badge;
                    private String sound;

                    @Override
                    public String toString() {
                        return "FcmAps{"
                                + ", badge=" + badge
                                + ", sound='" + sound
                                + '}';
                    }
                }
            }
        }
    }

    public static FireBaseTokenRequest fireBaseTokenRequestBuilder(String token, Data data, int badge, String title, String body) {
        FireBaseTokenRequest request = new FireBaseTokenRequest();

        FcmMessage.FcmNotification fcmNotification = new FcmMessage.FcmNotification();
        fcmNotification.setTitle(title != null ? title : "");
        fcmNotification.setBody(body != null ? body : "");

        FireBaseTokenRequest.FcmMessage.FcmApns.FcmPayload.FcmAps aps = new FireBaseTokenRequest.FcmMessage.FcmApns.FcmPayload.FcmAps();
        aps.setBadge(badge);
        aps.setSound("default");

        FireBaseTokenRequest.FcmMessage.FcmApns.FcmPayload payload = new FireBaseTokenRequest.FcmMessage.FcmApns.FcmPayload();
        payload.setAps(aps);

        FireBaseTokenRequest.FcmMessage.FcmApns apns = new FireBaseTokenRequest.FcmMessage.FcmApns();
        apns.setPayload(payload);

        FireBaseTokenRequest.FcmMessage message = new FireBaseTokenRequest.FcmMessage();
        message.setToken(token);
        message.setData(data);
        message.setApns(apns);
        message.setNotification(fcmNotification);

        request.setMessage(message);
        return request;
    }

    @Override
    public String toString() {
        return "FireBaseTokenRequest{"
                + "message=" + message.getApns().getPayload().getAps()
                + '}';
    }
}