// 소수들의 곱셈

import java.util.PriorityQueue;
import java.util.Scanner;

public class P0003 {
    
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int k,n;
        int[] sosu;

        for(int test_case=0; test_case < T; test_case++) {
            k = sc.nextInt();
            n = sc.nextInt();
            sosu = new int[k];
            PriorityQueue<Integer> pq= new PriorityQueue<>();

            for(int i=0; i<k; i++){
                sosu[i] = sc.nextInt();
                pq.add(sosu[i]);
            }
            
            int min = 0;
            for(int i=0; i<n; i++) {
                min = pq.poll();
                for(int j: sosu) {
                    pq.add(min*j);

                    if(min % j ==0) break;
                }
            }

            System.out.println(min);
            
        }
    }
}

