package com.qiufeng.io;
import java.io.*;
import java.util.*;
import java.util.zip.*;
public class AsyncQZip extends OnErrorListenerState{
	public static final String CREATOR="QiuFeng";
	private HashSet onZipListener=new HashSet();
	public static interface OnZipListener{
		public void onZipFinished(File file);
		public boolean willContain(File file);
	}
	private HashSet onUnzipListener=new HashSet();
	public static interface OnUnzipListener{
		public void onUnzipFinished(File file);
		public boolean willContain(ZipEntry entry);
	}
	private File file;
	public AsyncQZip(File f){
		super();
		file=f;
	}
	public AsyncQZip(String path){
		super();
		file=new File(path);
	}
	public void setOnZipListener(OnZipListener listener){
		onZipListener=new HashSet();
		onZipListener.add(listener);
	}
	private void notifyZF(File fi){
		Iterator iter = onZipListener.iterator();  
		while (iter.hasNext()) {  
			OnZipListener listener = (OnZipListener) iter.next();  
			listener.onZipFinished(fi);  
		}  
	}
	public void setOnUnzipListener(OnUnzipListener listener){
		onUnzipListener=new HashSet();
		onUnzipListener.add(listener);
	}
	private void notifyUF(File fi){
		Iterator iter = onUnzipListener.iterator();  
		while (iter.hasNext()) {  
			OnUnzipListener listener = (OnUnzipListener) iter.next();  
			listener.onUnzipFinished(fi);  
		}  
	}
	private boolean notifyZC(File fi){
		Iterator iter = onZipListener.iterator();  
		boolean res=true;
		while (iter.hasNext()) {  
			OnZipListener listener = (OnZipListener) iter.next();  
			res=listener.willContain(fi);  
			break;
		}  
		return res;
	}
	private boolean notifyUC(ZipEntry fi){
		Iterator iter = onUnzipListener.iterator();  
		boolean res=true;
		while (iter.hasNext()) {  
			OnUnzipListener listener = (OnUnzipListener) iter.next();  
			res=listener.willContain(fi);  
			break;
		}  
		return res;
	}
	public boolean zip(String input, String output) throws Exception {  
        zip(output, new File(input));  
        return true;  
    }  

    public void zip(String output, File input) throws Exception {  
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(output));  
        zip(out, input, ""); 
        out.close();  
    }  
	public void zip(String output) throws Exception{
		zip(output,file);
	}
	public void zip(ZipOutputStream out,File f,String base) throws Exception{
		try{
			zip(out,f,base,true); 
			notifyZF(f);
		}catch(Exception e){
			notifyOE(e);
		}
	}
    private void zip(ZipOutputStream out, File f, String base,boolean nope) throws Exception {  
		//split dir and file
        if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            out.putNextEntry(new ZipEntry(base + "/"));//"/" means a directory.
            base = base.length() == 0 ? "" : base + "/";  
            for (int i = 0; i < fl.length; i++) {
				if(notifyZC(fl[i])){
                	zip(out, fl[i], base + fl[i].getName(),true);  
				}
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
	public void unzip(String zipFile,File file) throws Exception{
		unzip(zipFile,file.getPath());
	}
	public void unzip(File zipFile,File file) throws Exception{
		unzip(zipFile.getPath(),file.getPath());
	}
	public void unzip(File zipFile,String fileName) throws Exception{
		unzip(zipFile.getPath(),fileName);
	}
	public void unzip(String zipFile,String fileName) throws Exception{ 
		try{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
        ZipInputStream zin = new ZipInputStream(new BufferedInputStream(bis));
        ZipEntry entry;
        File targetFold = null;
		if(!new File(fileName).exists()){
			new File(fileName).mkdirs();
		}
		while ((entry = zin.getNextEntry()) != null) {
			//set the buffer
			if(notifyUC(entry)){
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
		}
		zin.close(); // close the stream
		notifyUF(new File(fileName));
		}catch(Exception e){
			notifyOE(e);
		}
	}
	public InputStream getFileInZip(String zip,String target) throws IOException{
		ZipFile zipFile = new ZipFile(zip);
		ZipEntry zipEntry = zipFile.getEntry(target);
		InputStream in = zipFile.getInputStream(zipEntry);
		return in;
	}
	public int getListCount(String zip) throws IOException{
		ZipFile zipFile = new ZipFile(zip);
		Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
		int count=0;
		while(zipEntrys.hasMoreElements()){
			ZipEntry zipEntry = zipEntrys.nextElement();
			count++;
		}
		return count;
	}
	public String[] getList(String zip) throws IOException{
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
		return "[AsyncQZip QZip]";
	}
}
