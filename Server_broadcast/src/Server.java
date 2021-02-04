import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	public static ArrayList<PrintWriter> m_OutputList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 m_OutputList = new ArrayList<PrintWriter>();
		try{
			System.out.println("server");
            ServerSocket s_socket = new ServerSocket(8888);
           
            
            while(true){
                Socket c_socket = s_socket.accept();
                ClientManagerThread c_thread = new ClientManagerThread();
                System.out.println(c_socket.getInetAddress()+":"+c_socket.getPort());
                c_thread.setSocket(c_socket);

                m_OutputList.add(new PrintWriter(new BufferedWriter(new OutputStreamWriter(c_socket.getOutputStream(),"UTF-8")),true));
                System.out.println(m_OutputList.size());
                c_thread.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

	}

}
