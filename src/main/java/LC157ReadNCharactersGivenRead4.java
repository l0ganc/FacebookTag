import java.util.Random;

public class LC157ReadNCharactersGivenRead4 {
    /**
     * Given a file and assume that you can only read the file using a given method read4,
     * implement a method to read n characters.
     *  read4:
     *   * The read4 API is defined in the parent class Reader4.
     *      *     int read4(char[] buf);
     *
             Parameter:  char[] buf
             Returns:    int

             Note: buf[] is destination not source, the results from read4 will be copied to buf[]
     *
     *     Parameters:	char[] buf, int n
     *     Returns:	int
     *
     *      Note: buf[] is destination not source, you will need to write the results to buf[]
     */

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];  //Store our read chars from Read4
        int total = 0;

        while (total < n) {
            /*Read and store characters in temp. Count will store total chars read from Read4*/
            int count = read4(temp);

            /*Even if we read 4 chars from Read4,
            we don't want to exceed n and only want to read chars till n.*/
            count = Math.min(count, n - total);

            //Transfer all the characters read from Read4 to our buffer
            for (int i = 0; i < count; i++) {
                buf[total++] = temp[i];
            }

            //done. We can't read more characters.
            if (count < 4) {
                break;
            }
        }
        return total;
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



