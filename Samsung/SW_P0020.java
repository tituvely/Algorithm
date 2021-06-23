//교육P-0020. 달리기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_P0020 {
    static int T, N;
    static long[] runCapa;
    static long cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stz;

        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            runCapa = new long[N];

            stz = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                runCapa[i] = Integer.parseInt(stz.nextToken());
            }

            cnt = 0;
            mergeSort(runCapa, 0, runCapa.length - 1);

            System.out.println("#"+ t + " " + cnt);
        }
    }    

    public static void mergeSort(long[] array, int start, int end) {
        if(start >= end) return;

        int mid = (start + end) / 2;

        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, mid+1, end);
    }

    public static void merge (long[] array, int lstart, int lend, int rstart, int rend) {
        int lmin, rmin, indexTemp;
        long[] temp = new long[(rend-lstart)+1];

        lmin = lstart;
        rmin = rstart;
        indexTemp = 0;

        while (lmin <= lend && rmin <= rend) {
            if (array[lmin] < array[rmin]) {
                temp[indexTemp++] = array[rmin++];
                cnt = cnt + (lend - lmin + 1); 
            } else {
                temp[indexTemp++] = array[lmin++];
            }
        }

        if(lmin > lend) {
            for(int i=rmin; i<=rend; i++) {
                temp[indexTemp++] = array[i];
            }
        } else {
            for(int i=lmin; i<=lend; i++) {
                temp[indexTemp++] = array[i];
            }
        }

        for(int i = lstart; i<=rend; i++) {
            array[i] = temp[i-lstart];
        }
    }

}