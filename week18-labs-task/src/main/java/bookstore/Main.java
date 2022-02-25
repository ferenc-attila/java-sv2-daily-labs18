package bookstore;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;

public class Main {

    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/bookstore?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().locations("db/migration").dataSource(dataSource).load();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);

//        booksRepository.insertBook("Fekete István", "Vuk", 3400, 10);
//        booksRepository.insertBook("Fekete István", "Téli berek", 3600, 8);
//        booksRepository.insertBook("Fekete Péter", "Kártyatrükkök", 1200, 2);

        booksRepository.updatePieces(1L, 30);
        System.out.println(booksRepository.findBooksByWriter("Fekete"));
    }
}
