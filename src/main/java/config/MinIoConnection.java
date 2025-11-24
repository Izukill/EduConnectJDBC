package config;

import io.minio.MinioClient;

public class MinIoConnection {

    private static MinioClient client;

    public MinIoConnection() {
    }

    public static MinioClient getClient() {
        if (client == null) {
            client = MinioClient.builder()
                    .endpoint("http://localhost:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();
        }
        return client;
    }


}
