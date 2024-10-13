import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean f;
        System.out.println("""
                Login,password and confirm password should contain only:
                - letters of the Latin alphabet of any case
                - digits and sign '_'
                - length isn't more than 20 characters""");
        do{
            System.out.println("Enter login: ");
            String login = sc.next();
            System.out.println("Enter password: ");
            String password = sc.next();
            System.out.println("Enter confirm password: ");
            String passwordConf = sc.next();
            f = isValidInput(login,password,passwordConf);
        }while(!f);
        System.out.println(ANSI_CYAN + "All is correct!" + ANSI_RESET);
    }
    private static boolean isValidInput(String login,String password, String password1){
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*_).{1,20}$";
        String ANSI_RESET = "\u001B[0m";
        boolean f1 = Pattern.matches(regex, login);
        boolean f2 = Pattern.matches(regex, password);
        boolean f3 = Pattern.matches(regex, password1);
        boolean f4 = password.equals(password1);
        try {
            if (!f1){
                throw new WrongLoginException(ANSI_RED + "Login is incorrect!" + ANSI_RESET);
            }
            if(!f2){
                throw new WrongPasswordException(ANSI_RED + "Password is incorrect!" + ANSI_RESET);
            }
            if (!f3){
                throw new WrongPasswordException(ANSI_RED + "Confirm password is incorrect!" + ANSI_RESET);
            }
            if(!f4){
                throw new WrongPasswordException(ANSI_RED + "Passwords aren't equal!" + ANSI_RESET);
            }
            return true;
        }catch (WrongLoginException | WrongPasswordException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}

