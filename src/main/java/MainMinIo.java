import MinIoService.QuestoesMinIo;

public class MainMinIo {


    public static void main(String[] args) {

        try {

            QuestoesMinIo service = new QuestoesMinIo();


            String bucket = "simulados";//nome do bucket pro minIo


            String nomeObject = "questoes-prova-1.pdf";//nome do objeto do pdf

            //TODO adicionar caminho para salvar um pdf real de questões
            String caminhoLocal = "C:\\Users\\Documents\\questoes.pdf";

            //chama o upload
            service.uploadFile(bucket, nomeObject, caminhoLocal);

            System.out.println("Upload realizado");

        } catch (Exception e) {
            System.err.println("Erro ao enviar arquivo para o MinIO:");
            e.printStackTrace();
        }

    }
}
