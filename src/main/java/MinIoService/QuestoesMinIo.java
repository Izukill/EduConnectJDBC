package MinIoService;

import config.MinIoConnection;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;

public class QuestoesMinIo {

    private final MinioClient minioClient;

    public QuestoesMinIo() {
        this.minioClient = MinIoConnection.getClient();
    }

    public void uploadFile(String bucket, String nomeObject, String caminhoArquivo) throws Exception {

        //cria um bucket caso não exista
        //bucket são onde são salvos os arquivos no minIo
        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucket).build());

        if (!exists) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket(bucket).build());
        }

        //da upload do arquivo das questões
        minioClient.uploadObject(
                UploadObjectArgs.builder()
                        .bucket(bucket)
                        .object(nomeObject) //nome no MinIO
                        .filename(caminhoArquivo) //caminho local
                        .build()
        );
    }




}
