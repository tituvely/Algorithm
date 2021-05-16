// #1227. 미로2
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Position {
    int x;
    int y;

    Position() {}

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class SW1227 {
    static int map[][] = new int[100][100];
    static Queue<Position> q;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./SW1227_input.txt"));

        Scanner sc = new Scanner(System.in);
        
        for(int test_case=1; test_case<=10; test_case++) {  
            sc.next();
            String temp = "";
            int poss = 0;
        
            for(int i=0; i<100; i++) {  
                temp = sc.next();
                for(int j=0; j<100; j++) {  
                    map[i][j] = temp.charAt(j) - '0';
                }
            }

            q = new LinkedList<>();
            Position cur = new Position(1, 1);

            q.offer(cur);

            map[1][1] = -1;

            while(!q.isEmpty()) {
                cur = q.poll();
                
                //아래쪽
                if(cur.x + 1 < 100) {
                    if(map[cur.x + 1][cur.y] == 0) {
                        map[cur.x + 1][cur.y] = -1;
                        q.offer(new Position(cur.x+1, cur.y));
                    } else if(map[cur.x + 1][cur.y] == 3) {
                        poss = 1;
                        break;
                    }
                }

                //오른쪽
                if(cur.y + 1 < 100) {
                    if(map[cur.x][cur.y + 1] == 0) {
                        map[cur.x][cur.y + 1] = -1;
                        q.offer(new Position(cur.x, cur.y + 1));
                    } else if(map[cur.x][cur.y + 1] == 3) {
                        poss = 1;
                        break;
                    }
                }

                //위쪽
                if(cur.x - 1 >= 0) {
                    if(map[cur.x - 1][cur.y] == 0) {
                        map[cur.x - 1][cur.y] = -1;
                        q.offer(new Position(cur.x-1, cur.y));
                    } else if(map[cur.x - 1][cur.y] == 3) {
                        poss = 1;
                        break;
                    }
                }

                //왼쪽
                if(cur.y - 1 >= 0) {
                    if(map[cur.x][cur.y - 1] == 0) {
                        map[cur.x][cur.y - 1] = -1;
                        q.offer(new Position(cur.x, cur.y - 1));
                    } else if(map[cur.x][cur.y - 1] == 3) {
                        poss = 1;
                        break;
                    }
                } 
            }

            System.out.println("#" + test_case + " " + poss);
        }
    }
}
