import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("primeiroprojetojsf");

    }

}
