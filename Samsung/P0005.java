// 확장 유클리드 호제법

public class P0005 {
    static void EEA(int a, int b, int c) {
        int r0=0, r1=b; //r=ax+by ->r0=a ->r0=a*1+b*0
        int s0=1, s1=0; //r1=b -> r1=a*0+b*1
        int t0=0, t1=1;
        int temp=0, q=0;

        //0,1 0->n-1, 1->n  0->n    1->n+1
        while(r1!=0) {
            q=r0/r1;
            temp=r0;
            r0=r1;
            r1=temp-r1*q;

            //0->n-1, 1->n  0->n, 1->n+1
            temp=s0;
            s0=s1;
            s1=temp-s1*q;

            temp=t0;
            t0=t1;
            t1=temp-t1*q;
        }
        //as+bt=gcd(a,b) -> (s,t)쌍이 구해짐 = (s0,t0)
        //as+bt=n*gcd(a,b)*n ->(s0*n,t0*n)이 해가 됨
    }
}
