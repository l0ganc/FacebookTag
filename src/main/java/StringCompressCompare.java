public class StringCompressCompare {

    public static boolean numeronym(String s, String t){
        if(s == null)return t == null;
        int j = 0;
        int num= 0;
        for(int i = 0;i< s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(s.charAt(i))){
                num = num*10+s.charAt(i)-'0';
            }
            if(!Character.isDigit(c)){
                if(num != 0){
                    j += num;
                }
                if(j > t.length())return false;
                if(c != t.charAt(j))return false;
                else{
                    j++;
                }
                num = 0;
            }
        }
        if(num != 0){
            j+= num;
        }
        System.out.println(j);
        if(j != t.length())return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(numeronym("f3b3", "facebook"));
    }
}
