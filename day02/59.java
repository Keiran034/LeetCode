package day02;

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] mat = new int[n][n];
        int x = 0;
        int y = 0;
        // 0-right, 1-down, 2-left, 3-up
        int dir = 0;
        int num_cyc = 0;
        for(int i=1; i<=n*n; i++){
            mat[x][y] = i;
            if(dir == 0){
                y ++;
                if(y == n - num_cyc - 1){
                    dir = 1;
                }
            }
            else if(dir == 1){
                x ++;
                if(x == n - num_cyc - 1){
                    dir = 2;
                }
            }
            else if(dir == 2){
                y --;
                if(y == num_cyc){
                    dir = 3;
                    num_cyc ++;
                }
            }
            else if(dir == 3){
                x --;
                if(x == num_cyc){
                    dir = 0;
                }
            }
        }

        return mat;
    }
}
