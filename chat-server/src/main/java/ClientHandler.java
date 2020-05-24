import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class ClientHandler implements Callable<String> {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;
    private boolean running;
    private DBwork worker;
    private final long TIMELIMITE = 120000 / 1000;
    private boolean authTocken;
    private long timer;


    public ClientHandler(Socket socket, String nickName) throws IOException {
        this.socket = socket;
        this.nickName = nickName;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        running = true;
        //welcome();
        //  broadCastMessage("Поприветствум нового юзера! -> " + nickName);
        worker = new DBwork();
        this.timer = System.currentTimeMillis() / 1000;
        this.authTocken = true;
    }

    public String getNickName() {
        return nickName;
    }

    private void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private void welcome() throws IOException {
        out.writeUTF("Hello /" + nickName + transmitUsers());
        out.flush();
    }

    private void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : Server.getClients()) {
            if (!client.equals(this)) {
                client.sendMessage(message);
            }
        }
    }

    //функция для сообщения всем, что новый пользователь у
    private String transmitUsers() {
        //надо сделать чтобы не каждый раз заного строить список,А просто сравнивать есть или нету и добавлять всего один элемент TODO
        String usersOnline = "";
        for (ClientHandler client : Server.getClients()) {
            if (!client.equals(this)) {
                usersOnline += "&" + client.nickName;
            }
        }
        return usersOnline;
    }

    //TODO в трех места идёт одна и та же проверка на то, чтобы найти текущег юзера. надо будет сделать это как одну функцию и результат уже передавать куда надо.
    private void sendAuthWrongAttempt() throws IOException {
        //newAttemptToSignIn@/
        String message = "newAttemptToSignIn@/НЕУДАЧНАЯ ПОПЫТКА ВХОДА. ПРОВЕРЬТИ ВАШ ЛОГИН И ПАРОЛЬ";
        this.sendMessage(message);


    }

    private void privateMessage(String[] message) throws IOException {
        String userTochat = message[2].replace(" ", ""); //replace так как не понимаю откуда берутся пробелы у меня, надо будет разобраться, но пока такая ратификация
        String userFrom = message[1];
        String privateMessage = message[3];
        for (ClientHandler client : Server.getClients()) {
            if (client.getNickName().equals(userTochat)) {
                client.sendMessage("приватное сообщение от " + "[" + userFrom + "]: " + privateMessage);
            }
        }
    }

    private void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    private void declineUser() {

    }

    @Override
    public String call() throws IOException {
        while (running) {

            try {
                String clientMessage = in.readUTF();
                //   String clientMessage = in.readUTF();
                if (clientMessage.equals("anonymousUser")) {
                    this.sendMessage("Вы не авторизованы, сервере у вас есть 120с");


                    while (authTocken) {

                        PushbackInputStream pbi = new PushbackInputStream(in);
                        //int bites;
                        DataInputStream dis = new DataInputStream(pbi);
                        while (pbi.available() == 0) {
                            //  pbi.unread(bites);
                            if ((System.currentTimeMillis() / 1000 - timer) % 4 == 0) {
                                System.out.println("Вы не авторизованы, сервере у вас есть 120с. Прошло " + (System.currentTimeMillis() / 1000 - timer) + " cекунд");
                                //не понимаю почему не отображается это сообщение у клиента через sendMessage, пробовал и с this etc и так же, но при всем при том самое первое сообщение о том
                                //что не авторизовались выше, он отравляет нормально....и ответ от юзера с паролем и ником то же...........
                            //    this.sendMessage("Вы не авторизованы, сервере у вас есть 120с. Прошло " + (System.currentTimeMillis() / 1000 - timer) + " cекунд");
                            }

                            if (((System.currentTimeMillis() / 1000) - timer) > TIMELIMITE) {
                                this.sendMessage("Ваше время истекло. Вы отключены от сервера");
                                Server.getClients().remove(this);
                                running = false;
                                this.in.close();
                                InetAddress tmpAdress = socket.getInetAddress();
                                this.socket.close();
                                System.out.println("the end");
                                return "Поток с пользователя с адрессом: " + tmpAdress + "завершен, так кака пользователь не авторизовался";
                            }
                        }
                        checkUser(dis.readUTF());

                    }

                } else if (clientMessage.equals("_exit_")) {
                    Server.getClients().remove(this);
                    sendMessage(clientMessage);
                    System.out.println("удалили юзера");
                    break;
                } else if (clientMessage.contains("nick/")) {
                    String nick[] = clientMessage.split("/");
                    setNickName(nick[1]);
                    sendMessage("newNickName/" + getNickName());
                    //TODO сделать рассылку всем с измененным ником. сейчас не сделано
                } else if (clientMessage.contains("privateMessage@//")) {
                    String[] splitPrivateMessage = clientMessage.split("@//");

                    //можно придумать лучше или сделать джоин всего после первого если вдруг попадется последовательность @// в сообщении, но я сомневаюмь, оставлю пока так
                    privateMessage(splitPrivateMessage);
                } else if (clientMessage.contains("serverAuth@/")) {
                    String[] spltMsg = clientMessage.split("@/");
                    //просто для проверки посомтреть на данные
                    String login = spltMsg[2];
                    String psw = spltMsg[1];
                    System.out.println(login + "/" + psw);
                    //-----------------------------------------
                    //TODO так как в в serverAuth и signIn ниже, есть повторяющиеся строки с доступом и заданим значений в запросе к БД, то сделать одну функцию в которуб будет передавать
                    // и там уже выбирать запись или добавление

                    if (spltMsg[0].equals("serverAuth")) {
                        try {
                            if (!worker.getConnection().isClosed()) {
                                // Statement statement = worker.getConnection().createStatement();
                                // String preparedInsertquery = "INSERT INTO chat_users(login,password) VALUES (?,?')";
                                worker.getConnection().setAutoCommit(false);
                                PreparedStatement prpStm = worker.getConnection().prepareStatement("INSERT INTO chat_users(login,password) VALUES (?,?)");
                                prpStm.setString(1, login);
                                prpStm.setString(2, psw);
                                prpStm.executeUpdate();
                                worker.getConnection().commit();
                                System.out.println("Данные успешно добавлены");
                                prpStm.close();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        setNickName(login);
                    }
                    authTocken = false;

                } else if (clientMessage.contains("signIn@/")) {                //TODO не забыть убрать кусок кода ниже и поместить в фунцию, т.к тот же кусок используется еще в 1ом месте
                    String[] spltMsg = clientMessage.split("@/");
                    //просто для проверки посомтреть на данные
                    String login = spltMsg[2];
                    String psw = spltMsg[1];
                    System.out.println(login + "/" + psw);
                    try {
                        if (!worker.getConnection().isClosed()) {
                            //worker.getConnection().setAutoCommit(false);
                            ResultSet rs;
                            PreparedStatement prpStm = worker.getConnection().prepareStatement("SELECT * FROM chat_users WHERE login=? AND password =?");
                            prpStm.setString(1, login);
                            prpStm.setString(2, psw);
                            rs = prpStm.executeQuery();
                            // worker.getConnection().commit();
                            String loginTest = "";
                            String pasTest = "";
                            while (rs.next()) {
                                //TODO сделать класс для описание полученного из БД
                                loginTest = rs.getString(2);
                                pasTest = rs.getString(4);
                                System.out.println("получили - " + loginTest + " and " + pasTest);

                            }
                            setNickName(loginTest);
                            if (!loginTest.isEmpty() && !pasTest.isEmpty()) {
                                System.out.println("совпали");
                                welcome();
                                authTocken = false;
                                broadCastMessage("Поприветствум нового юзера! -> " + nickName);
                            } else {
                                sendAuthWrongAttempt();
                            }
                            rs.close();
                            prpStm.close();


                        }


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } //TODO сделать кусок для изменения ника в БД  statement.executeUpdate("UPDATE  chat_users SET nickName = ? WHERE  id = ?") id получить текущем поиском по логину или ....
                else {
                    System.out.println(clientMessage);
                    broadCastMessage(clientMessage);
                }

                //   } else {
                //     Server.getClients().remove(this); //надо подумать где закрыть сокет TODO
                //   running = false;
                // }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "Поток с пользователя с адрессом: " + socket.getInetAddress() + "завершен";

    }

    private void checkUser(String checkInf) {
        String[] spltMsg = checkInf.split("@/");
        //просто для проверки посомтреть на данные
        String login = spltMsg[2];
        String psw = spltMsg[1];
        System.out.println(login + "/" + psw);
        try {
            if (!worker.getConnection().isClosed()) {
                //worker.getConnection().setAutoCommit(false);
                ResultSet rs;
                PreparedStatement prpStm = worker.getConnection().prepareStatement("SELECT * FROM chat_users WHERE login=? AND password =?");
                prpStm.setString(1, login);
                prpStm.setString(2, psw);
                rs = prpStm.executeQuery();
                // worker.getConnection().commit();
                String loginTest = "";
                String pasTest = "";
                while (rs.next()) {
                    //TODO сделать класс для описание полученного из БД
                    loginTest = rs.getString(2);
                    pasTest = rs.getString(4);
                    System.out.println("получили - " + loginTest + " and " + pasTest);

                }
                setNickName(loginTest);
                if (!loginTest.isEmpty() && !pasTest.isEmpty()) {
                    System.out.println("совпали");
                    authTocken = false;
                    welcome();
                    broadCastMessage("Поприветствум нового юзера! -> " + nickName);
                } else {
                    sendAuthWrongAttempt(); //TODO либо дописать, лиюо убрать
                }
                rs.close();
                prpStm.close();

            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
