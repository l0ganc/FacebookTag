public class LC158ReadNCharactersGivenRead4IICallMultipleTimes {
    /**
     * LC157 follow up 多次调用read n
     */

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */

    public int count = 0;  // read4读取的字符个数
    public int pointer = 0;   // temp数组里的指针
    public char[] temp = new char[4];   // 存放read4读取的结果buf

    public int read(char[] buf, int n) {
        int index = 0;
        while (index < n) {
            if (pointer == 0) {   // 重新调用read4
                count = read4(temp);
            }

            if (count == 0) break;  // 所有字符已经读完

            while (index < n && pointer < count) {  // 填充
                buf[index++] = temp[pointer++];
            }

            if (pointer == count) pointer = 0;  // 当前的temp里字符已经全部读完，需要重新调用read4
        }
        return index;
    }


    // fake read4 method just for running
    public int read4(char[] temp) {
        char[] res = new char[10];
        char[] ret = new char[4];

        int index = 0;
        for (int i = 0; i < res.length; i++) {
            if (index < 4) {
                ret[index++] = temp[i];
            }
        }
        return index;
    }
}
