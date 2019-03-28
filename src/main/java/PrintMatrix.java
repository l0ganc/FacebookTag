public class PrintMatrix {
    /**
     * 像花卷那样打印一个matrix，是逆时针方向
     *
     */


    //写了简洁的供参考
    //r: means the radius, +1 every two turn
    //turn: means how many turns it finished
    //r starts from 2, and at the beginning of each turn it is already printed by last turn's last spot, so skip it.

    int[] print(int[][]matrix, int col, int row){
        int count =0;
        int m = matrix.length,n=matrix[0].length;
        int[]ans = new int[m*n];
        //->up->left->down->right
        int[]dx = {0,-1,0,1,0};
        int[]dy = {0,0, -1,0,1};
        int r=2, turn=0;
        if(col>=0&&col<m && row>=0 && row<n){
            ans[count++]=matrix[col][row];
        }
        while(count<m*n){
            int cdirX = dx[turn%4+1];
            int cdirY = dy[turn%4+1];

            for(int i=1;i<r;i++){
                col = col+cdirX;
                row = row+cdirY;
                if(col>=0&&col<m && row>=0 && row<n){
                    ans[count++]=matrix[col][row];
                    if(count==m*n)
                        return ans;
                }
            }
            turn++;
            r+=(turn%2==0?1:0);
        }
        return ans;
    }

}
