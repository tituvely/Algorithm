// * Time complexity
// Best: O(n^2) Avg: O(n^2) Worst: O(n^2)
// Best: O(n) -> 교환이 일어나지 않을 때, 빠져나가는 조건을 추가한다면
////// O(n) is the best-case running time for bubble sort.
////// It is possible to modify bubble sort to keep track of the number of swaps it performs.
////// If an array is already in sorted order, and bubble sort makes no swaps,
////// the algorithm can terminate after one pass.
// * Space complexity
// O(1) - 주어진 배열 안에서 교환(In-place Sort)
// * 완전정렬(Stable sort) - 동일한 값에 대해 기존의 순서가 유지되는 정렬 방식

public class Bubble {
    public void sort(int[] data) {
        int size = data.length;
        int temp = 0;
        
        for(int i=0; i < size; i++) {
           for(int j=1; j < size-i; j++) {
                if(data[j-1] > data[j]) {
                    temp = data[j];
                    data[j] = data[j-1];
                    data[j-1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Bubble bubble = new Bubble();

        int[] data = {3, 5, 1, 7, 2, 4};

        bubble.sort(data);

        for (int i : data) {
            System.out.println(i);
        }
    }
}
