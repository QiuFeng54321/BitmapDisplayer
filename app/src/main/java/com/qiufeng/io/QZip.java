package com.qiufeng.io;
import java.io.*;
import java.util.zip.*;
import java.util.Enumeration;
public class QZip{
	public static final String CREATOR="QiuFeng";
	public static boolean zip(String input, String output) throws Exception {  
        zip(output, new File(input));  
        return true;  
    }  

    public static void zip(String output, File input) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));  
        zip(out, input, ""); 
        out.close();  
    }  

    public static void zip(ZipOutputStream out, File f, String base) throws Exception {  
		//split dir and file
        if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            out.putNextEntry(new ZipEntry(base + "/"));//"/" means a directory.
            base = base.length() == 0 ? "" : base + "/";  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + fl[i].getName());  
            }  
        } else {  
            out.putNextEntry(new ZipEntry(base));//put the entry.
            FileInputStream in = new FileInputStream(f);  
            int b;
            while ((b = in.read()) != -1) {  
                out.write(b);  
            }  
            in.close();  
        }  
    }  
	public static void unzip(String zipFile,File file) throws Exception{
		unzip(zipFile,file.getPath());
	}
	public static void unzip(File zipFile,File file) throws Exception{
		unzip(zipFile.getPath(),file.getPath());
	}
	public static void unzip(File zipFile,String fileName) throws Exception{
		unzip(zipFile.getPath(),fileName);
	}
	public static void unzip(String zipFile,String fileName) throws Exception{ 
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
        ZipInputStream zin = new ZipInputStream(new BufferedInputStream(bis));
        ZipEntry entry;
        File targetFold = null;
            while ((entry = zin.getNextEntry()) != null) {
                //set the buffer
                int BUFFER = 2048;
                String tmp = fileName + entry.getName();
                //create target
                targetFold = new File(tmp);
                if((!targetFold.exists() || targetFold.isFile())&&entry.isDirectory()){
                    //(new File(tmp)).mkdirs();
                    targetFold.mkdirs();
                }
                if(entry.isDirectory()){
                    continue;
                }
                FileOutputStream fos = new FileOutputStream(targetFold);

                BufferedOutputStream dest = new BufferedOutputStream(fos,BUFFER);

                // read the File
                int count = 0;
                byte[] data = new byte[BUFFER];
                while ((count = zin.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();// refresh
                dest.close();// close the stream
            }
            zin.close(); // close the stream
	}
	public static InputStream getFileInZip(String zip,String target) throws IOException{
		ZipFile zipFile = new ZipFile(zip);
		ZipEntry zipEntry = zipFile.getEntry(target);
		InputStream in = zipFile.getInputStream(zipEntry);
		return in;
	}
	public static int getListCount(String zip) throws IOException{
		ZipFile zipFile = new ZipFile(zip);
		Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
		int count=0;
		while(zipEntrys.hasMoreElements()){
			ZipEntry zipEntry = zipEntrys.nextElement();
			count++;
		}
		return count;
	}
	public static String[] getList(String zip) throws IOException{
		int count=getListCount(zip);
		String[] res=new String[count];
		int current=0;
		ZipFile zipFile = new ZipFile(zip);
		Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
		while(zipEntrys.hasMoreElements()){
			ZipEntry zipEntry = zipEntrys.nextElement();
			res[current]=zipEntry.getName();
			current++;
		}
		return res;
	}
	@Override
	public String toString(){
		return "[QZip QZip]";
	}
}
