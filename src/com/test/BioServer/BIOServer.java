package com.test.BioServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class BIOServer {
	
	public static void main(String[] args) throws IOException
	{
		ExecutorService threadPool = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(8080);
		System.out.println("服务器已经启动");
		while(true)
		{
			final Socket socket=serverSocket.accept();
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					handleConnection(socket);
				}
			});
		}
		
	}
	
	public static void handleConnection(Socket socket)
	{
		String thread_nameString = Thread.currentThread().getName();
		System.out.println("当前线程名："+thread_nameString);
		InetSocketAddress remoAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remotHost = remoAddress.getHostString();
		System.out.println(remotHost+ "已经连接");
		InputStream inputStream = null;
		try {
			byte[] cacheBytes = new byte[1024];
			inputStream= socket.getInputStream();
			while(true)
			{
				int count = inputStream.read(cacheBytes);
				if(count == -1) break;
				String reciveDataString = new String(cacheBytes,0,count);
				if("q".equals(reciveDataString)) break;
				System.out.println("接收到来自"+remotHost+" "+count+" 字节信息： "+reciveDataString);	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("获取连接客户端输入流异常");
		}finally {
			try {
				if(inputStream!=null)
				{
					inputStream.close();
				}
				socket.close();
			} catch (Exception e2) {
				System.out.println("关闭流错误");
			}
			
		}
	}

}
