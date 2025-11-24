package entidades;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Presenca {

    private ObjectId objetoId; //usado como ponteiro para o mongoDb
    private int id;
    private int id_aula; //FK
    private int id_aluno;//FK
    private LocalDate dataAula;

    private String status;

    public Presenca() {
    }

    public Presenca(int id, String status, int id_aula, int id_aluno) {
        this.id = id;
        this.status = status;
        this.id_aula = id_aula;
        this.id_aluno = id_aluno;
    }

    public Presenca( int id_aluno, int id_aula, ObjectId objetoId, String status) {
        this.id_aluno = id_aluno;
        this.id_aula = id_aula;
        this.objetoId = objetoId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_turma() { //mudar para id de aula e não de turma
        return id_aula;
    }


    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public void setId_aula(int id_aula) {
        this.id_aula = id_aula;
    }

    public void setObjetoId(ObjectId objetoId) {
        this.objetoId = objetoId;
    }

    public LocalDate getDataAula() {
        return dataAula;
    }

    public void setDataAula(LocalDate dataAula) {
        this.dataAula = dataAula;
    }
}

