package com.test.NIOServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChanelTest {
	
	public static void main(String[] args) throws Exception
	{
		String filePath = "C:\\Users\\Administrator\\Desktop\\filechanel.txt";
		read_from_file(filePath);
	}
	
	
	public static void write_to_file(String filePath) throws Exception
	{
		String string = new String("hello file chanel");
		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		FileChannel fileChannel = fileOutputStream.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		buffer.put(string.getBytes());
		buffer.flip();
		fileChannel.write(buffer);
		fileOutputStream.close();
		fileChannel.close();
	}
	
	public static void read_from_file(String filePath) throws Exception
	{
		FileInputStream file_in = new FileInputStream(filePath);
		FileChannel file_in_chanel = file_in.getChannel();
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		System.out.println("文件大小："+file_in_chanel.size());
		while(true)
		{
			int count = file_in_chanel.read(readBuffer);
			System.out.println("当前读取的字节数："+count);
			if(count == -1) break;
			String data = new String(readBuffer.array());
			System.out.println(data);
			readBuffer.clear();
		}
		
		file_in.close();
		file_in_chanel.close();
	}

}
