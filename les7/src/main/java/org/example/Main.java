package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            connectDB();
          //Создание таблиц БД
//            createTableMedicines();
//            createTableEmployee();
//            createTableSupplier();
//            createTableDelivery();

            // создание триггерной функции и триггера для таблицы delivery
//            createTriggerFunctionAndTrigger();

            while (true){
                System.out.println(""" 
                        \s
                        1. Вставить значения \s
                        2. Вывести содержимое таблиц \s
                        3. Удалить значения \s
                        4. Обновить значения \s
                        5. Выйти
                       """);
                int ch = sc.nextInt();

                int ch2;
                String name;
                int count;
                int id;

                switch (ch){
                    case 1:
                       boolean f1 = false;
                        while (!f1) {
                            System.out.println("""
                                    Выберите таблицу: \s
                                    1. medicines \s
                                    2. employee \s
                                    3. supplier \s
                                    4. delivery
                                    """);

                            ch2 = sc.nextInt();
                            switch (ch2) {
                                case 1:
                                    f1 = true;
                                    System.out.println("Введите название и количество лекарства:");
                                    name = sc.next();
                                    count = sc.nextInt();
                                    insertMedicines(name, count);
                                    break;
                                case 2:
                                    f1 = true;
                                    System.out.println("Введите фамилию, имя, должность:");
                                    String surname = sc.next();
                                    name = sc.next();
                                    String post = sc.next();
                                    insertEmployee(surname, name, post);
                                    break;
                                case 3:
                                    f1 = true;
                                    System.out.println("Введите наименование:");
                                    name = sc.next();
                                    insertSupplier(name);
                                    break;
                                case 4:
                                    f1 = true;
                                    System.out.println("Введите дату, количество лекарства, id лекарства, " +
                                            "id поставщика, id сотрудника");
                                    System.out.println("Пример даты: '2024-11-11'");
                                    String date = sc.next();
                                    count = sc.nextInt();
                                    int med_id = sc.nextInt();
                                    int sup_id = sc.nextInt();
                                    int emp_id = sc.nextInt();
                                    insertDelivery(date, count, med_id, sup_id, emp_id);
                                    break;
                                default:
                                    System.out.println("Неправильный ввод!");
                                    break;
                            }
                        }
                        break;
                    case 2:
                        boolean f2 = false;
                        String nameTable = "";
                        while (!f2) {
                            System.out.println("""
                                    Выберите таблицу: \s
                                    1. medicines \s
                                    2. employee \s
                                    3. supplier \s
                                    4. delivery
                                    """);
                            ch2 = sc.nextInt();
                            switch (ch2) {
                                case 1:
                                    nameTable = "medicines";
                                    f2 = true;
                                    break;
                                case 2:
                                    nameTable = "employee";
                                    f2 = true;
                                    break;
                                case 3:
                                    nameTable = "supplier";
                                    f2 = true;
                                    break;
                                case 4:
                                    nameTable = "delivery";
                                    f2 = true;
                                    break;
                                default:
                                    System.out.println("Неправильный ввод!");
                                    break;
                            }
                        }
                        selectAllFromTable(nameTable);
                        break;
                    case 3:
                        boolean f3 = false;
                        while (!f3) {
                            System.out.println("""
                                    Выберите таблицу: \s
                                    1. medicines \s
                                    2. employee \s
                                    3. supplier \s
                                    4. delivery
                                    """);

                            ch2 = sc.nextInt();

                            switch (ch2) {
                                case 1:
                                    f3 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    deleteMedicines(id);
                                    break;
                                case 2:
                                    f3 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    deleteEmployee(id);
                                    break;
                                case 3:
                                    f3 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    deleteSupplier(id);
                                    break;
                                case 4:
                                    f3 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    deleteDelivery(id);
                                    break;
                                default:
                                    System.out.println("Неправильный ввод!");
                                    break;
                            }
                        }
                        break;
                    case 4:
                        boolean f4 = false;
                        while (!f4) {
                            System.out.println("""
                                    Выберите таблицу: \s
                                    1. medicines \s
                                    2. employee \s
                                    3. supplier \s
                                    4. delivery
                                    """);

                            ch2 = sc.nextInt();

                            switch (ch2) {
                                case 1:
                                    f4 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    System.out.println("Введите название и количество лекарства:");
                                    name = sc.next();
                                    count = sc.nextInt();
                                    updateMedicines(id, name, count);
                                    break;
                                case 2:
                                    f4 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    System.out.println("Введите фамилию, имя, должность:");
                                    String surname = sc.next();
                                    name = sc.next();
                                    String post = sc.next();
                                    updateEmployee(id, surname, name, post);
                                    break;
                                case 3:
                                    f4 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    System.out.println("Введите наименование:");
                                    name = sc.next();
                                    updateSupplier(id, name);
                                    break;
                                case 4:
                                    f4 = true;
                                    System.out.println("Введите id записи:");
                                    id = sc.nextInt();
                                    System.out.println("Введите дату, количество лекарства, id лекарства, " +
                                            "id поставщика, id сотрудника");
                                    System.out.println("Пример даты: '2024-11-11'");
                                    String date = sc.next();
                                    count = sc.nextInt();
                                    int med_id = sc.nextInt();
                                    int sup_id = sc.nextInt();
                                    int emp_id = sc.nextInt();
                                    updateDelivery(id, date, count, med_id, sup_id, emp_id);
                                    break;
                                default:
                                    System.out.println("Неправильный ввод!");
                                    break;
                            }
                        }
                        break;
                    case 5:
                        disconnect();
                        System.exit(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }

    }

    private static void connectDB(){
        String url = "";
        String user = "";
        String password = "";
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение к БД успешно!");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Не получилось подключиться к БД!");
            e.printStackTrace();
        }
    }

    private static void disconnect(){
        try {
            if(statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(connection != null) {
                connection.close();
                System.out.println("Вы отключились от БД!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTableMedicines() throws SQLException {
        statement.executeUpdate("""
                CREATE TABLE medicines (
                id INTEGER PRIMARY KEY NOT NULL,\s
                name CHARACTER VARYING(100) NOT NULL,\s
                count INTEGER NOT NULL\s
                );""");
    }

    private static void createTableEmployee() throws SQLException {
        statement.executeUpdate("""
                CREATE TABLE employee (\s
                id INTEGER PRIMARY KEY NOT NULL,\s
                surname CHARACTER VARYING(50) NOT NULL,\s
                name CHARACTER VARYING(50) NOT NULL,\s
                post CHARACTER VARYING(50) NOT NULL\s
                );""");
    }

    private static void createTableSupplier() throws SQLException {
        statement.executeUpdate("""
                CREATE TABLE supplier (\s
                id INTEGER PRIMARY KEY NOT NULL,\s
                name CHARACTER VARYING(50) NOT NULL
                );""");
    }

    private static void createTableDelivery() throws SQLException {
        statement.executeUpdate("""
                CREATE TABLE delivery (\s
                id INTEGER PRIMARY KEY NOT NULL,\s
                data DATE NOT NULL,  \s
                count INTEGER NOT NULL, \s
                medicines_id INTEGER NOT NULL, \s
                supplier_id INTEGER NOT NULL, \s
                employee_id INTEGER NOT NULL, \s
                CONSTRAINT fk_med FOREIGN KEY(medicines_id) REFERENCES medicines(id) \s
                ON DELETE CASCADE ON UPDATE CASCADE, \s
                CONSTRAINT fk_sup FOREIGN KEY(supplier_id) REFERENCES supplier(id) \s
                ON DELETE CASCADE ON UPDATE CASCADE, \s
                CONSTRAINT fk_emp FOREIGN KEY(employee_id) REFERENCES employee(id) \s
                ON DELETE CASCADE ON UPDATE CASCADE
                );""");
    }

    private static void createTriggerFunctionAndTrigger() throws SQLException {
        statement.executeUpdate("""
                CREATE OR REPLACE FUNCTION func_update_count_medicines()\s
                RETURNS TRIGGER AS $$ \s
                BEGIN \s
                    UPDATE medicines \s
                    SET count = count + NEW.count \s
                    WHERE id = NEW.medicines_id; \s
                    RETURN NEW; \s
                END; \s
                $$ LANGUAGE plpgsql;
                """);

        statement.executeUpdate("""
                CREATE TRIGGER update_medicines \s
                AFTER INSERT \s
                ON delivery \s
                FOR EACH ROW \s
                EXECUTE PROCEDURE func_update_count_medicines(); \s
                """);
    }

    private static void selectAllFromTable(String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;
        ResultSet set = statement.executeQuery(query);
        ResultSetMetaData metaData = set.getMetaData();

        int columnCount = metaData.getColumnCount();

        for(int i = 1; i <= columnCount; i++){
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        while (set.next()){
            for(int i = 1; i <= columnCount; i++){
                System.out.print(set.getString(i) + "\t");
            }
            System.out.println();
        }
    }

    private static void insertMedicines(String name, int count) throws SQLException {
        String queryIns = "INSERT INTO medicines (id, name, count) VALUES (?, ?, ?)";
        int id = getLastId("medicines") + 1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryIns)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, count);

            preparedStatement.executeUpdate();
        }
    }

    private static void  insertEmployee(String surname, String name, String post) throws SQLException {
        int id = getLastId("employee") + 1;
        String queryIns = "INSERT INTO employee(id, surname, name, post) VALUES (?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(queryIns)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, post);

            preparedStatement.executeUpdate();
        }
    }

    private static void insertSupplier(String name) throws SQLException {
        int id = getLastId("supplier") + 1;
        String queryIns = "INSERT INTO supplier(id, name) VALUES (?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(queryIns)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        }
    }

    private static void insertDelivery(String date, int count, int med_id, int supp_id, int emp_id) throws SQLException {
        int id = getLastId("delivery") + 1;
        String queryIns = "INSERT INTO delivery(id, data, count, medicines_id, supplier_id, employee_id) VALUES (?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(queryIns)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, Date.valueOf(date));
            preparedStatement.setInt(3, count);
            preparedStatement.setInt(4, med_id);
            preparedStatement.setInt(5, supp_id);
            preparedStatement.setInt(6, emp_id);

            preparedStatement.executeUpdate();
        }
    }

    private static int getLastId(String tableName) throws SQLException {
        int lastId = -1;
        String sql = "SELECT MAX(id) AS max_id FROM " + tableName;

        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            lastId = resultSet.getInt("max_id");
        }

        return lastId;
    }

    private static void updateMedicines(int id, String name, int count) throws SQLException {
        String queryUpdate = "UPDATE medicines SET name = ?, count = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, count);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void updateEmployee(int id, String surname, String name, String post) throws SQLException {
        String queryUpdate = "UPDATE employee SET surname = ?, name = ?, post = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {
            preparedStatement.setString(1, surname);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, post);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void updateSupplier(int id, String name) throws SQLException {
        String queryUpdate = "UPDATE supplier SET name = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void updateDelivery(int id, String date, int count, int med_id, int supp_id, int emp_id) throws SQLException {
        String queryUpdate = "UPDATE delivery SET data = ?, count = ?, medicines_id = ?, supplier_id = ?, employee_id = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryUpdate)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setInt(2, count);
            preparedStatement.setInt(3, med_id);
            preparedStatement.setInt(4, supp_id);
            preparedStatement.setInt(5, emp_id);
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void deleteMedicines(int id) throws SQLException {
        String queryDel = "DELETE FROM medicines WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryDel)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void deleteEmployee(int id) throws SQLException {
        String queryDel = "DELETE FROM employee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryDel)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void deleteSupplier(int id) throws SQLException {
        String queryDel = "DELETE FROM supplier WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryDel)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }

    private static void deleteDelivery(int id) throws SQLException {
        String queryDel = "DELETE FROM delivery WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryDel)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
