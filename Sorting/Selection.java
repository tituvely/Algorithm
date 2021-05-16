//* Time complexity
// Best: O(n^2) Avg: O(n^2) Worst: O(n^2)
//* Space complexity
// O(1) - 주어진 배열 안에서 교환(In-place Sort)
//* 불완전정렬(Unstable sort) - 동일한 값에 대해 기존의 순서가 유지되지 않는 정렬 방식

public class Selection {
    public void sort(int[] data) {
        int size = data.length;
        int min;    //최소값을 가진 데이터의 인덱스 저장 변수
        int temp;

        for(int i=0; i < size-1; i++) {
            min = i;
            for(int j=i+1; j < size; j++) {
                if(data[min] > data[j]) {
                    min = j;
                }
            }
            temp = data[min];
            data[min] = data[i];
            data[i] = temp;
        }
    }
    public static void main(String[] args) {
        Selection selection = new Selection();

        int data[] = {9, 4, 3, 5, 1};

        selection.sort(data);

        for (int i : data) {
            System.out.println(i);
        }
    }
}
    