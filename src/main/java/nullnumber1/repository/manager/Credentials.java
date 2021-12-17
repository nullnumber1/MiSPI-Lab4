package nullnumber1.repository.manager;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

@ApplicationScoped
@Slf4j
public class Credentials {
    private String username = "";
    private String password = "";

    @PostConstruct
    public void init() {
        try {
            String path = "credentials.txt";
            File file = new File(path);

            String paath = file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());
            Scanner scanner = new Scanner(file);
            username = scanner.nextLine().trim();
            password = scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            log.error("in Credentials init() – credentials file is incorrect or don't exist");
        } catch (Exception e) {
            log.error("{} exception in {this.getClass().getName()}", e.toString());
        }
    }

    public String getUsername() {
        return "postgres";
    }

    public String getPassword() {
        return "202145";
    }
}
