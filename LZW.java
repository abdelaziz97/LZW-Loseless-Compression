package lzw;
import java.util.*;
import java.io.*;
import java.util.Vector;
import java.io.IOException;


public class LZW {

    public static Vector<Integer> V =new Vector<Integer>();
    public static void compress(String in){
        
       int index=127;
       HashMap<String,Integer> map=new HashMap<String,Integer>();
       for (int i=0 ; i<128 ;i++)
       {
           map.put(Character.toString((char)i) , i);
       }
           
       String p = "",temp;
       for (int i=0 ; i<in.length() ;i++){
           char c=in.charAt(i);
           temp=p+c;
           if (!map.containsKey(temp))
           {
               index++;
               map.put(temp, index);
               V.addElement(map.get(p));
               p=Character.toString(c);    
           }
           else 
               p+=c;
           
           if(i==in.length()-1 && map.containsKey(temp))
           {
               V.addElement(map.get(p));
           }
       }
       
       for(int i=0 ; i<V.size() ; i++)
       {
           System.out.println(V.elementAt(i));
       }
       write();
    }
    public static String decompress()
    {
        HashMap<Integer,String> map=new HashMap<Integer,String>();
        for (int i=0 ; i<128 ;i++)
        {
           map.put(i,Character.toString((char)i));
        }
        read();
        String result="", preStr="",currStr="",pPLUSc="";
        int index=127, pre=-1,curr=-1;
        for (int i=0 ; i<V.size() ; i++)
        {
            if (map.containsKey(V.elementAt(i))) // normal case that the letter found in hashmap
            {
               // for the cases that previous is empty
               currStr=map.get(V.elementAt(i));
               result+=currStr;
               pre=curr;
               curr=V.elementAt(i);
               if( pre != -1) // for normal cases that previous has a value
               {
                   curr=V.elementAt(i);
                   preStr=map.get(pre);
                   currStr=map.get(curr);
                   pPLUSc=preStr+currStr.charAt(0);
                   index++;
                   map.put(index, pPLUSc);
               }
            }
            else // if the index is not found in the hashmap
            {
                currStr=map.get(curr);
                pPLUSc=currStr+currStr.charAt(0);
                result+=pPLUSc;
                index++;
                curr=index;
                map.put(index, pPLUSc);
            }
        }
        System.out.println(result);
        return result;
        
    }
    
     public static void write()
     {
         try{
            
            FileWriter x = new FileWriter ("zizo.txt");
            for(int i=0 ; i<V.size() ; i++)
            {
                x.write(V.elementAt(i) + " ");
            }
            x.close();
        }
        catch(IOException m) 
        {System.out.print("error");}
     }
     public static void read()
     {
         try{
             V=new Vector<Integer>();
             FileReader x=new FileReader("zizo.txt");
             Scanner convert=new Scanner(x);
             while(convert.hasNext())
             {
                 V.addElement(convert.nextInt());
             }
             x.close();
         }
        catch(IOException m) 
        {System.out.print("error");}
     }
     

    public static void main(String[] args) {
        // TODO code application logic here
        LZWJframe run = new LZWJframe();
        run.setVisible(true);
    }
    
}
