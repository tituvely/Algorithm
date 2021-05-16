//* Time complexity
// Best: O(n^2) Avg: O(n^2) Worst: O(n^2)
//* Space complexity
// O(1) - 주어진 배열 안에서 교환(In-place Sort)
//* 완전정렬(Stable sort) - 동일한 값에 대해 기존의 순서가 유지되는 정렬 방식

public class Insertion {
    public void sort(int[] data) {
        int size = data.length;

        for(int i=1; i < size; i++) {
            int key = data[i];
            int j = i-1;
            while((j >= 0) && (data[j] > key)){
                data[j+1] = data[j];
                j--;
            }
            data[j+1] = key;
        }
    }

    public static void main(String[] args) {
        Insertion insertion = new Insertion();

        int data[] = {5, 2, 3, 1, 9};
        
        insertion.sort(data);

        for (int i : data) {
            System.out.println(i);
        }

    }
}
