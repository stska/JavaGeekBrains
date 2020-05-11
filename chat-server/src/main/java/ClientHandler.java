import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickName;
    private boolean running;


    public ClientHandler(Socket socket, String nickName) throws IOException {
        this.socket = socket;
        this.nickName = nickName;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        running = true;
        welcome();
        broadCastMessage("Поприветствум нового юзера! -> " + nickName);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void welcome() throws IOException {
        out.writeUTF("Hello /" + nickName + transmitUsers());
        out.flush();
    }

    public void broadCastMessage(String message) throws IOException {
        for (ClientHandler client : Server.getClients()) {
            if (!client.equals(this)) {
                client.sendMessage(message);
            }
        }
    }
    //функция для сообщения всем, что новый пользователь у
    public String transmitUsers(){
        //надо сделать чтобы не каждый раз заного строить список,А просто сравнивать есть или нету и добавлять всего один элемент TODO
        String usersOnline = "";
        for(ClientHandler client : Server.getClients()){
            if (!client.equals(this)) {
            usersOnline += "&" + client.nickName;
            }
        }
        return usersOnline;
    }
    public void privateMessage(String [] message) throws IOException {
        String userTochat =  message[2].replace(" ",""); //replace так как не понимаю откуда берутся пробелы у меня, надо будет разобраться, но пока такая ратификация
        String userFrom = message[1];
        String privateMessage =  message[3];
        for(ClientHandler client : Server.getClients()){
            if(client.getNickName().equals(userTochat)){
                client.sendMessage("приватное сообщение от " + "[" + userFrom + "]: " + privateMessage);
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (socket.isConnected()) {
                    String clientMessage = in.readUTF();
                    if (clientMessage.equals("_exit_")) {
                        Server.getClients().remove(this);
                        sendMessage(clientMessage);
                        System.out.println("удалили юзера");
                        break;
                    }
                    else if(clientMessage.contains("nick/")){
                        String nick[] = clientMessage.split("/");
                        setNickName(nick[1]);
                        sendMessage("newNickName/" + getNickName());
                        //TODO сделать рассылку всем с измененным ником. сейчас не сделано
                    } else if(clientMessage.contains("privateMessage@//")){
                        String[] splitPrivateMessage = clientMessage.split("@//");

                        //можно придумать лучше или сделать джоин всего после первого если вдруг попадется последовательность @// в сообщении, но я сомневаюмь, оставлю пока так
                       privateMessage(splitPrivateMessage);
                    } else{
                        System.out.println(clientMessage);
                        broadCastMessage(clientMessage);
                    }

                } else {
                    Server.getClients().remove(this); //надо подумать где закрыть сокет TODO
                    running = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
