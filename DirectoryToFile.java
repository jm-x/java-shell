// 以下に必要な記述を追加せよ
import java.io.*;
public class DirectoryToFile{
    static String dstfile;
    static long dirnum=0;
    static long filenum=0;
    static PrintWriter writer=null;
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("使用法：java DirectoryToFile path dstFIle");
            System.out.println("例：java FindFilename ./ dirs.txt");
            System.exit(0);
        }
        try{
        writer=new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        }catch(IOException e){
            System.out.println("ファイルを用意できませんでした。");
            System.exit(0);
        }
        // System.out.println(args[0]);
        writer.println(args[0]);
        writeTree(args[0],0);
        // System.out.println("\n"+dirnum+" directories, "+filenum+" files\n");
        writer.println("\n"+dirnum+" directories, "+filenum+" files\n");
        writer.close();
	return;
    }


    static void writeTree(String path,int depth){//num:自身がなんばん目に呼ばれたものか
        String[] ls=new File(path).list();
        for(int i=0;i<ls.length;i++){

            //権限のないものは例外となる
            try{

                File tmp=new File(path+ls[i]);
                if(!tmp.isDirectory()){
                    for(int j=0;j<2*(depth+1);j++){
                        if(j>2*(depth)+1){
                            // System.out.print("─");
                            writer.print("─");
                            continue;
                        }
                        // System.out.print(" ");
                        writer.print(" ");
                    }
                    // System.out.println(ls[i]);
                    writer.println(ls[i]);
                    filenum++;
                    continue;
                }
                if(tmp.getName().equals(".")){
                    continue;
                }
                if(tmp.getName().equals("..")){
                    continue;
                }

                for(int j=0;j<2*(depth+1);j++){
                    // System.out.print(" ");
                    writer.print(" ");
                }
                // System.out.println(ls[i]+"/");
                writer.println(ls[i]+"/");
                dirnum++;
                writeTree(path+ls[i]+"/",depth+1);

            }catch(NullPointerException  e){
                // System.out.println("\u0007");//ベル文字
            }
            
        }
    }
}
