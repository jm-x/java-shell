// 以下に必要な記述を追加せよ
import java.io.*;

public class FindFilename{
    static String keyword;
    static String startPath;
    
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("使用法：java FindFilename path keyword");
            System.out.println("例：java FindFilename ~/ txt");
            System.exit(0);
        }
        keyword=args[1];
    
        searchDir(args[0]);
        
        
	return;
    }
    static void searchDir(String path){//pathは相対パス ./ ~/ 最後がバックスラッシュ
        String[] ls=new File(path).list();
        for(int i=0;i<ls.length;i++){

            //権限のないものは例外となる
            try{

                File tmp=new File(path+ls[i]);
                if(!tmp.isDirectory()){
                    if(tmp.getName().indexOf(keyword)!=-1){
                        String str=new File(path+tmp.getName()).getAbsolutePath();
                            if(str.indexOf("/../")!=-1){
                            String[] str2=str.split("/../",-1);
                            String rev=new StringBuffer(str2[0]).reverse().toString();
                            rev=rev.substring(rev.indexOf("/")+1);
                            str2[0]=new StringBuffer(rev).reverse().toString();
                            System.out.println(str2[0]+"/"+str2[1]);
                        }else{
                            System.out.println(str);
                        }
                    }
                    continue;
                }
                if(tmp.getName().equals(".")){
                    continue;
                }
                if(tmp.getName().equals("..")){
                    continue;
                }
                searchDir(path+ls[i]+"/");

            }catch(NullPointerException  e){

            }
            
        }
    }

    // static void CleanCopy(){
    // }
}
